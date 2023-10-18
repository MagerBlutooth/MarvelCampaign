package adventure.model.adventure;

import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

public class DeckProfileList implements Iterable<CardList> {

    List<CardList> profiles;
    int latestProfileNum;

    public DeckProfileList(int profileCount)
    {
        profiles = new ArrayList<>();
        profileCount = Math.max(0, profileCount);
        for(int i = 0; i < profileCount; i++)
        {
            profiles.add(new CardList(new ArrayList<>()));
        }
        latestProfileNum = 0;
    }

    public String toSaveString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(CardList list: profiles)
        {
            stringBuilder.append(list.toSaveString());
            stringBuilder.append(SnapMainConstants.CATEGORY_SEPARATOR);
        }
        if(!profiles.isEmpty())
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String saveString = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(saveString.getBytes());
    }

    public void fromSaveString(String saveString, TargetDatabase<Card> cardDatabase)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(saveString);
        String decodedString = new String(decodedBytes);
        if(decodedString.isBlank())
            return;
        String[] cardsList = decodedString.split(SnapMainConstants.CATEGORY_SEPARATOR);

        for(int i = 0; i < cardsList.length; i++)
        {
            String cString = cardsList[i];
            CardList deck = new CardList(new ArrayList<>());
            deck.fromSaveString(cString, cardDatabase);
            setProfile(i, deck);
        }
    }

    @Override
    public Iterator<CardList> iterator() {
        return profiles.iterator();
    }

    public int size() {
        return profiles.size();
    }

    public CardList getProfile(int p) {
        if(p < 0 || p >= size())
            p = 0;
        return profiles.get(p);
    }

    public void setLatestProfileNum(int l)
    {
       latestProfileNum = l;
    }

    public CardList getLatestProfile()
    {
        return getProfile(latestProfileNum);
    }

    public int getLatestProfileNum() {
        return latestProfileNum;
    }

    public void setProfile(int profileNum, CardList deck) {
        profiles.set(profileNum, deck.cloneNewList(deck.getThings()));
    }
}
