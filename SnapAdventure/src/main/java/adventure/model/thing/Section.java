package adventure.model.thing;

import adventure.model.AdventureDatabase;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.PlayableDatabase;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.*;

import java.util.ArrayList;
import java.util.Base64;

public class Section implements Cloneable, SnapTarget {
    AdvLocation advLocation;
    CardList stationedCards;
    PlayableList pickups;
    PlayableDatabase cardsAndTokens;
    TargetDatabase<Card> cardDatabase;
    boolean revealed;
    boolean completed;
    int sectionNum;
    Enemy enemy;

    public Section(int num, PlayableDatabase ct, Enemy e)
    {
        sectionNum = num;
        stationedCards = new CardList(new ArrayList<>());
        pickups = new PlayableList(new ArrayList<>());
        cardsAndTokens = ct;
        enemy = e;
    }

    public Section(int num, AdventureDatabase database, Enemy e){
        this(num, database.getCardsAndTokens(), e);
    }


    public Section(int num, AdvLocation l, AdventureDatabase database, Enemy e)
    {
        sectionNum = num;
        advLocation = l;
        stationedCards = new CardList(new ArrayList<>());
        pickups = new PlayableList(new ArrayList<>());
        cardsAndTokens = database.getCardsAndTokens();
        cardDatabase = database.getCards();
        revealed = false;
        enemy = e;
    }

    public Section(Section loc) {
        sectionNum = loc.sectionNum;
        advLocation = loc.advLocation;
        stationedCards = new CardList(new ArrayList<>());
        pickups = new PlayableList(new ArrayList<>());
        cardsAndTokens = loc.cardsAndTokens;
        revealed = loc.revealed;
        completed = loc.completed;
        cardDatabase = loc.cardDatabase;
        enemy = loc.enemy;
    }


    public String toSaveString() {
        String result = sectionNum + SnapMainConstants.SUBCATEGORY_SEPARATOR +
                advLocation.getID() +
                SnapMainConstants.SUBCATEGORY_SEPARATOR +
                stationedCards.toSaveString() +
                SnapMainConstants.SUBCATEGORY_SEPARATOR +
                pickups.toSaveString() + SnapMainConstants.SUBCATEGORY_SEPARATOR +
                revealed + SnapMainConstants.SUBCATEGORY_SEPARATOR +
                completed;
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String saveString, TargetDatabase<AdvLocation> locations) {
        byte[] decodedBytes = Base64.getDecoder().decode(saveString);
        String decodedString = new String(decodedBytes);
        if(decodedString.isBlank())
            return;
        String[] stringList = decodedString.split(SnapMainConstants.SUBCATEGORY_SEPARATOR);
        sectionNum = Integer.parseInt(stringList[0]);
        advLocation = locations.lookup(Integer.parseInt(stringList[1]));
        stationedCards.fromSaveString(stringList[2], cardDatabase);
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
    public boolean hasAttribute(String entry) {
        return advLocation.hasAttribute(entry);
    }

    @Override
    public Section clone() {
        return new Section(this);
    }

    public void stationCard(Card c) {
        stationedCards.add(c);
    }

    public AdvLocation getLocation() {
        return advLocation;
    }

    public TargetList<Playable> getPickups() {
        return pickups;
    }

    public TargetList<Card> getStationedCards()
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

    public boolean hasStationedCards() {
        return !stationedCards.isEmpty();
    }

    public boolean hasPickups()
    {
        return !getPickups().isEmpty();
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
