package adventure.model.thing;

import snapMain.model.constants.CampaignConstants;
import snapMain.model.database.PlayableDatabase;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.*;

import java.util.ArrayList;
import java.util.Base64;

public class Section implements Cloneable, Target {
    AdvLocation advLocation;
    PlayableList stationedCards;
    PlayableList pickups;
    PlayableDatabase cardsAndTokens;
    boolean revealed;
    boolean completed;
    int sectionNum;

    public Section(int num){
        sectionNum = num;
        stationedCards = new PlayableList(new ArrayList<>());
        pickups = new PlayableList(new ArrayList<>());
    }

    public Section(int num, AdvLocation l, PlayableDatabase pD)
    {
        sectionNum = num;
        advLocation = l;
        stationedCards = new PlayableList(new ArrayList<>());
        pickups = new PlayableList(new ArrayList<>());
        cardsAndTokens = pD;
        revealed = false;
    }

    public Section(Section loc) {
        sectionNum = loc.sectionNum;
        advLocation = loc.advLocation;
        stationedCards = new PlayableList(new ArrayList<>());
        pickups = new PlayableList(new ArrayList<>());
        cardsAndTokens = loc.cardsAndTokens;
        revealed = loc.revealed;
        completed = loc.completed;
    }

    public String toSaveString() {
        String result = sectionNum + CampaignConstants.SUBCATEGORY_SEPARATOR +
                advLocation.getID() +
                CampaignConstants.SUBCATEGORY_SEPARATOR +
                stationedCards.toSaveString() +
                CampaignConstants.SUBCATEGORY_SEPARATOR +
                pickups.toSaveString() + CampaignConstants.SUBCATEGORY_SEPARATOR +
                revealed + CampaignConstants.SUBCATEGORY_SEPARATOR +
                completed;
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String saveString, TargetDatabase<AdvLocation> locations) {
        byte[] decodedBytes = Base64.getDecoder().decode(saveString);
        String decodedString = new String(decodedBytes);
        if(decodedString.isBlank())
            return;
        String[] stringList = decodedString.split(CampaignConstants.SUBCATEGORY_SEPARATOR);
        sectionNum = Integer.parseInt(stringList[0]);
        advLocation = locations.lookup(Integer.parseInt(stringList[1]));
        stationedCards.fromSaveString(stringList[2], cardsAndTokens);
        pickups.fromSaveString(stringList[3], cardsAndTokens);
        revealed = Boolean.parseBoolean(stringList[4]);
        completed = Boolean.parseBoolean(stringList[5]);
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
        advLocation.setID(i);
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

    public void complete()
    {
        completed = true;
    }

    public void changeLocation(AdvLocation loc)
    {
        advLocation = loc;
    }

    public String getEffect() {
        return advLocation.getEffect();
    }

    public int getSectionNum() {
        return sectionNum;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void addPickup(InfinityStone infinityStone) {
        pickups.add(infinityStone);
    }
}
