package adventure.model.target;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureDatabase;
import adventure.model.Team;
import adventure.model.target.base.AdvLocation;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.*;

import java.util.ArrayList;
import java.util.Base64;

public class Section implements Cloneable, SnapTarget {
    AdvLocation advLocation;
    ActiveCardList stationedCards;
    PlayableList rewards;
    PlayableDatabase cardsAndTokens;
    AdventureDatabase adventureDatabase;
    boolean revealed;
    boolean completed;
    int sectionNum;
    Enemy enemy;

    private Section()
    {
        super();
        advLocation = new AdvLocation(new Location());
        stationedCards = new ActiveCardList(new ArrayList<>());
        rewards = new PlayableList(new ArrayList<>());
    }

    public Section(AdventureDatabase database, int num, AdvLocation l, Enemy e)
    {
        this();
        sectionNum = num;
        advLocation = l;
        cardsAndTokens = database.getEnemySubjects();
        adventureDatabase = database;
        revealed = false;
        enemy = e;
    }

    public Section(Section loc) {
        sectionNum = loc.sectionNum;
        advLocation = loc.advLocation;
        stationedCards = new ActiveCardList(new ArrayList<>());
        rewards = new PlayableList(new ArrayList<>());
        cardsAndTokens = loc.cardsAndTokens;
        revealed = loc.revealed;
        completed = loc.completed;
        adventureDatabase = loc.adventureDatabase;
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
                rewards.toSaveString() + SnapMainConstants.SUBCATEGORY_SEPARATOR +
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
        stationedCards.fromSaveString(stringList[3], adventureDatabase.getCards());
        rewards.fromSaveString(stringList[4], cardsAndTokens);
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

    public void stationCard(ActiveCard c) {
        stationedCards.add(c);
    }

    public AdvLocation getLocation() {
        return advLocation;
    }

    public TargetList<Playable> getRewards() {
        return rewards;
    }

    public TargetList<ActiveCard> getStationedCards()
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

    public void addReward(InfinityStone infinityStone) {
        rewards.add(infinityStone);
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy e) {
        enemy = e;
    }

    public SnapTarget replaceEnemy(Enemy e) {
        SnapTarget oldEnemy = enemy.getSubject();
        enemy = e;
        return oldEnemy;
    }

    public boolean removeStationedCard(ActiveCard card, Team t) {
        boolean removed = stationedCards.remove(card);
        if(removed)
            t.addCardToTeam(card);
        return removed;
    }

    public Enemy enemyEscapes() {
        Enemy escapingEnemy = new Enemy(enemy);
        enemy = new Enemy();
        return escapingEnemy;
    }

    public ActiveCard unstationCard(ActiveCard c) {
        stationedCards.remove(c);
        return c;
    }

    public ActiveCardList unstationCards() {
        ActiveCardList unstationingCards = new ActiveCardList();
        unstationingCards.addAll(stationedCards);
        stationedCards.clear();
        return unstationingCards;
    }

    public String toString()
    {
        if(this instanceof BossSection)
            return "Boss Section";
        return "Section " + sectionNum;
    }
}
