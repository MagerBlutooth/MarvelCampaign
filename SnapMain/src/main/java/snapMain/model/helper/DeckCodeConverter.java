package snapMain.model.helper;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;

import java.util.ArrayList;
import java.util.Base64;

public class DeckCodeConverter {

    public CardList convertDeckCodeToDeck(TargetDatabase<Card> cardDatabase, String encodedString)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        CardList cards = new CardList(new ArrayList<>());
        String decodedString = new String(decodedBytes);
        String[] splitString = decodedString.split("#");
        for(String s: splitString)
        {
            if(s.contains("("))
            {
                String substring = s.substring(s.lastIndexOf(")")+1).trim();
                Card c = cardDatabase.lookup(substring);
                cards.add(c);
            }
        }
        return cards;
    }

    public void encodeDeckToClipboard(String deckName, CardList deck)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(Card card: deck)
        {
            stringBuilder.append("# (").append(card.getCost()).append(") ").append(card.getName()).append("\n");
        }
        stringBuilder.append("# \n");
        stringBuilder.append(createEncodedString(deckName, deck)).append("\n");
        stringBuilder.append("#\n");
        stringBuilder.append("# To use this deck, copy it to your clipboard and paste it from the deck editing menu in Snap.");
        String copiedCode = stringBuilder.toString();
        ClipboardContent content = new ClipboardContent();
        content.putString(copiedCode);
        Clipboard.getSystemClipboard().setContent(content);
    }

    private String createEncodedString(String deckName, CardList deck) {
        StringBuilder encodedString = new StringBuilder();
        encodedString.append("{\"Name\":" + "\"").append(deckName).append("\",\"Cards\":[");
        for(Card card: deck)
        {
            String cardSimpleName = StringHelper.displayFormat(card.getName());
            encodedString.append("{\"CardDefId\":\"").append(cardSimpleName).append("\"},");
        }
        encodedString.replace(encodedString.length()-1, encodedString.length(), "");
        encodedString.append("]}");
        String result = encodedString.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }
}
