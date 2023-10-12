package adventure.model.thing;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureDatabase;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.PlayableDatabase;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.*;

import java.lang.annotation.Target;
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

    public Section(AdventureDatabase database, int num, AdvLocation l, Enemy e)
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
                enemy.toSaveString() +
                SnapMainConstants.SUBCATEGORY_SEPARATOR +
                stationedCards.toSaveString() +
                SnapMainConstants.SUBCATEGORY_SEPARATOR +
                pickups.toSaveString() + SnapMainConstants.SUBCATEGORY_SEPARATOR +
                revealed + SnapMainConstants.SUBCATEGORY_SEPARATOR +
                completed;
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String saveString, AdvMainDatabase database) {
        byte[] decodedBytes = Base64.getDecoder().decode(saveString);
        String decodedString = new String(decodedBytes);
        if(decodedString.isBlank())
            return;
        String[] stringList = decodedString.split(SnapMainConstants.SUBCATEGORY_SEPARATOR);
        sectionNum = Integer.parseInt(stringList[0]);
        TargetDatabase<AdvLocation> locDatabase = database.lookupDatabase(TargetType.LOCATION);
        advLocation = locDatabase.lookup(Integer.parseInt(stringList[1]));
        enemy = new Enemy();
        enemy.fromSaveString(stringList[2], database);
        stationedCards.fromSaveString(stringList[3], cardDatabase);
        pickups.fromSaveString(stringList[4], cardsAndTokens);
        revealed = Boolean.parseBoolean(stringList[5]);
        completed = Boolean.parseBoolean(stringList[6]);
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

    public void setEnemy(Enemy e) {
        enemy = e;
    }
}
