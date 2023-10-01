package adventure.model.thing;

import snapMain.model.constants.CampaignConstants;
import snapMain.model.database.PlayableDatabase;
import snapMain.model.database.TargetDatabase;
import snapMain.model.thing.*;

import java.util.ArrayList;
import java.util.Base64;

public class Section implements Cloneable, Target {
    AdvLocation advLocation;
    PlayableList stationedCards;
    PlayableList pickups;
    PlayableDatabase cardsAndTokens;
    boolean revealed;
    int id;

    public Section(){
        stationedCards = new PlayableList(new ArrayList<>());
        pickups = new PlayableList(new ArrayList<>());
    }

    public Section(AdvLocation l, PlayableDatabase pD)
    {
        advLocation = l;
        stationedCards = new PlayableList(new ArrayList<>());
        pickups = new PlayableList(new ArrayList<>());
        cardsAndTokens = pD;
        revealed = false;
    }

    public Section(Section loc) {
        advLocation = loc.advLocation;
        stationedCards = new PlayableList(new ArrayList<>());
        pickups = new PlayableList(new ArrayList<>());
        cardsAndTokens = loc.cardsAndTokens;
        revealed = loc.isRevealed();
    }

    public String toSaveString() {
        String result = advLocation.getID() +
                CampaignConstants.SUBCATEGORY_SEPARATOR +
                stationedCards.toSaveString() +
                CampaignConstants.SUBCATEGORY_SEPARATOR +
                pickups.toSaveString() +
                revealed;
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String saveString, TargetDatabase<AdvLocation> locations) {
        byte[] decodedBytes = Base64.getDecoder().decode(saveString);
        String decodedString = new String(decodedBytes);
        if(decodedString.isBlank())
            return;
        String[] stringList = decodedString.split(CampaignConstants.SUBCATEGORY_SEPARATOR);
        advLocation = locations.lookup(stringList[0]);
        stationedCards.fromSaveString(stringList[1], cardsAndTokens);
        pickups.fromSaveString(stringList[2], cardsAndTokens);
        revealed = Boolean.parseBoolean(stringList[3]);
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.LOCATION;
    }

    public int getID()
    {
        return advLocation.getID();
    }

    @Override
    public void setEnabled(boolean enabled) {

    }

    @Override
    public boolean isEnabled() {
        return advLocation.isEnabled();
    }

    @Override
    public String getName() {
        return advLocation.getName();
    }

    @Override
    public void setID(int i) {
        id = i;
    }

    @Override
    public Section clone() {
        return new Section(this);
    }

    public void addStation(Playable p) {
        stationedCards.add(p);
    }

    public AdvLocation getLocation() {
        return advLocation;
    }

    public PlayableList getPickups() {
        return pickups;
    }

    public PlayableList getStationedCards()
    {
        return stationedCards;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void reveal() {
        revealed = true;
    }
}
