package adventure.model.adventure;

import adventure.model.AdventureDatabase;
import adventure.model.target.ActiveCardList;
import snapMain.model.constants.SnapMainConstants;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

public class DeckProfileList implements Iterable<ActiveCardList> {

    List<ActiveCardList> profiles;
    int latestProfileNum;

    public DeckProfileList(int profileCount)
    {
        profiles = new ArrayList<>();
        profileCount = Math.max(0, profileCount);
        for(int i = 0; i < profileCount; i++)
        {
            profiles.add(new ActiveCardList(new ArrayList<>()));
        }
        latestProfileNum = 0;
    }

    public DeckProfileList(DeckProfileList profileList)
    {
        profiles = new ArrayList<>(profileList.profiles);
        latestProfileNum = profileList.latestProfileNum;
    }

    public String toSaveString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(ActiveCardList list: profiles)
        {
            stringBuilder.append(list.toSaveString());
            stringBuilder.append(SnapMainConstants.CATEGORY_SEPARATOR);
        }
        if(!profiles.isEmpty())
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String saveString = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(saveString.getBytes());
    }

    public void fromSaveString(String saveString, AdventureDatabase adventureDatabase)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(saveString);
        String decodedString = new String(decodedBytes);
        if(decodedString.isBlank())
            return;
        String[] cardsList = decodedString.split(SnapMainConstants.CATEGORY_SEPARATOR);

        for(int i = 0; i < cardsList.length; i++)
        {
            String cString = cardsList[i];
            ActiveCardList deck = new ActiveCardList(new ArrayList<>());
            deck.fromSaveString(cString, adventureDatabase.getCards());
            setProfile(i, deck);
        }
    }

    @Override
    public Iterator<ActiveCardList> iterator() {
        return profiles.iterator();
    }

    public int size() {
        return profiles.size();
    }

    public ActiveCardList getProfile(int p) {
        if(p < 0 || p >= size())
            p = 0;
        return profiles.get(p);
    }

    public void setLatestProfileNum(int l)
    {
       latestProfileNum = l;
    }

    public ActiveCardList getLatestProfile()
    {
        return getProfile(latestProfileNum);
    }

    public int getLatestProfileNum() {
        return latestProfileNum;
    }

    public void setProfile(int profileNum, ActiveCardList deck) {
        profiles.set(profileNum, deck.cloneNewList(deck.getThings()));
    }
}
