package snapMain.model.target;

import snapMain.model.constants.CampaignConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.sortFilter.CardSortMode;
import snapMain.model.sortFilter.CardSorter;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class CardList extends TargetList<Card> {

    CardSorter cardSorter = new CardSorter();

    public CardList(List<Card> cards)
    {
        super(cards);
    }

    public CardList(CardList cards)
    {
        super(cards);
        cardSorter = cards.cardSorter;
    }

    public void sort()
    {
        List<Card> sortedCards = cardSorter.sort(new ArrayList<>(getCards()));
        this.clear();
        this.addAll(sortedCards);
    }
    public void setSortMode(String m) {
        cardSorter.changeMode(CardSortMode.parseString(m));
    }

    public List<Card> getCards() {
        return getThings();
    }

    public String toCSVSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(Card c: getCards())
        {
            stringBuilder.append(c.getID());
            stringBuilder.append(CampaignConstants.STRING_SEPARATOR);
        }
        stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public void fromCSVSaveString(String cardString, TargetDatabase<Card> database)
    {
        String[] cardsList = cardString.split(CampaignConstants.STRING_SEPARATOR);

        for(String c: cardsList)
        {
            this.add(database.lookup(Integer.parseInt(c)));
        }
    }

    public String toSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(Card c: getCards())
        {
            stringBuilder.append(c.getID());
            stringBuilder.append(CampaignConstants.CSV_SEPARATOR);
            stringBuilder.append(c.isCaptain());
            stringBuilder.append(CampaignConstants.CSV_SEPARATOR);
            stringBuilder.append(c.isWounded());
            stringBuilder.append(CampaignConstants.STRING_SEPARATOR);
        }
        if(!getCards().isEmpty())
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        else
            stringBuilder.append(" ");
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String cardString, TargetDatabase<Card> database)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(cardString);
        String decodedString = new String(decodedBytes);
        if(decodedString.isBlank())
            return;
        String[] cardsList = decodedString.split(CampaignConstants.STRING_SEPARATOR);

        for(String cString: cardsList)
        {
            String[] cOptions = cString.split(CampaignConstants.CSV_SEPARATOR);
            Card card = new Card(database.lookup(Integer.parseInt(cOptions[0])));
            card.setCaptain(Boolean.parseBoolean(cOptions[1]));
            card.setWounded(Boolean.parseBoolean(cOptions[2]));
            this.add(card);
        }
    }

    @Override
    public void clear()
    {
        things.clear();
    }

    public String toCardListString() {
        StringBuilder stringBuilder = new StringBuilder();
        sort();
        for(Card c: getCards())
        {
            stringBuilder.append(c.getName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public int getCardIndex(Card captain) {
        return this.indexOf(captain);
    }

    public CardList cloneNewList(List<Card> existingList) {
        CardList clonedCards = new CardList(new ArrayList<>());
        for(Card c: existingList)
        {
            clonedCards.add(new Card(c));
        }
        return clonedCards;
    }
}
