package adventure.model;

import adventure.model.stats.AdvMatchResult;
import adventure.model.stats.WorldStatTracker;
import adventure.model.target.*;
import adventure.model.target.base.*;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.logger.MLogger;

import java.util.*;

public class World implements Cloneable{

    Section section1;
    Section section2;
    Section section3;
    Section section4;
    BossSection bossSection;
    AdventureDatabase database;
    int worldNum;
    int currentSectionNum;
    WorldBonusCalculator bonusCalculator;
    WorldStatTracker worldStatTracker;
    boolean bossRevealed;

    MLogger logger = new MLogger(World.class);

    public World(AdventureDatabase db)
    {
        database = db;
        bonusCalculator = new WorldBonusCalculator();
        worldStatTracker = new WorldStatTracker();
    }

    public World(AdventureDatabase db, List<AdvLocation> locations, int wNum)
    {
        this(db);
        worldNum = wNum;
        section1 = new Section(db, 1, locations.get(0) , new Enemy(new Mook(), bonusCalculator.calculateMook(worldNum)));
        section2 = new Section(db, 2, locations.get(1), new Enemy(new Mook(), bonusCalculator.calculateMook(worldNum)));
        section3 = new Section(db,3, locations.get(2), new Enemy(new Mook(), bonusCalculator.calculateMook(worldNum)));
        section4 = new Section(db,4, locations.get(3), new Enemy(new Mook(), bonusCalculator.calculateMook(worldNum)));
        bossSection = new BossSection( db, new Enemy(new Mook(), bonusCalculator.calculateBoss(worldNum)));
        section1.reveal();
        currentSectionNum = 1;
    }

    public World(World world) {
        database = world.database;
        section1 = world.section1;
        section2 = world.section2;
        section3 = world.section3;
        section4 = world.section4;
        bossSection = world.bossSection;
        bonusCalculator = world.bonusCalculator;
        worldNum = world.worldNum;
        bossRevealed = world.bossRevealed;
        worldStatTracker = world.worldStatTracker;
        currentSectionNum = world.currentSectionNum;
    }

    public String toSaveString() {
        String result = worldNum + SnapMainConstants.CATEGORY_SEPARATOR +
                currentSectionNum +
                SnapMainConstants.CATEGORY_SEPARATOR +
                section1.toSaveString() +
                SnapMainConstants.CATEGORY_SEPARATOR +
                section2.toSaveString() +
                SnapMainConstants.CATEGORY_SEPARATOR +
                section3.toSaveString() +
                SnapMainConstants.CATEGORY_SEPARATOR +
                section4.toSaveString() +
                SnapMainConstants.CATEGORY_SEPARATOR +
                bossSection.toSaveString() +
                SnapMainConstants.CATEGORY_SEPARATOR +
                bossRevealed + SnapMainConstants.CATEGORY_SEPARATOR +
                worldStatTracker.toSaveString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    //Initialize boss to be a card that the player doesn't currently own
    //Postpone initializing boss of future worlds so that boss is always a current free agent.
    public void initializeBoss(ActiveCardList freeAgents) {
        ActiveCardList agentsCopy = new ActiveCardList(new ArrayList<>());
        agentsCopy = agentsCopy.cloneNewList(freeAgents.getThings());
        Collections.shuffle(agentsCopy.getThings());
        TargetDatabase<AdvCard> bosses = database.getAdvCards();
        ActiveCard card = agentsCopy.get(0);
        AdvCard boss = bosses.lookup(card.getID());
        Enemy enemy = new Enemy(boss, bonusCalculator.calculateBoss(worldNum));
        bossSection.setEnemy(enemy);
        freeAgents.remove(card);
        bossRevealed = false;
    }

    public void fromSaveString(String saveString, AdvMainDatabase dB) {
        byte[] decodedBytes = Base64.getDecoder().decode(saveString);
        String decodedString = new String(decodedBytes);
        if(decodedString.isBlank())
            return;
        String[] stringList = decodedString.split(SnapMainConstants.CATEGORY_SEPARATOR);
        worldNum = Integer.parseInt(stringList[0]);
        currentSectionNum = Integer.parseInt(stringList[1]);
        section1 = new Section(database, 1, new Ruins(), new Enemy());
        section1.fromSaveString(stringList[2].trim(), dB);
        section2 = new Section(database, 2,  new Ruins(), new Enemy());
        section2.fromSaveString(stringList[3].trim(), dB);
        section3 = new Section( database, 3, new Ruins(), new Enemy());
        section3.fromSaveString(stringList[4].trim(), dB);
        section4 = new Section(database, 4, new Ruins(), new Enemy());
        section4.fromSaveString(stringList[5].trim(), dB);
        bossSection = new BossSection(database, new Enemy());
        bossSection.fromSaveString(stringList[6].trim(), dB);
        bossRevealed = Boolean.parseBoolean(stringList[7]);
        worldStatTracker = new WorldStatTracker();
        worldStatTracker.fromSaveString(stringList[8]);
    }

    @Override
    public World clone() {
        try {
            return (World) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Section getFirstSection() {
        return section1;
    }

    public Section getSecondSection()
    {
        return section2;
    }

    public Section getThirdSection()
    {
        return section3;
    }

    public Section getFourthSection()
    {
        return section4;
    }

    public Enemy getBoss() {
        return bossSection.getEnemy();
    }

    public Section getSection(int sectionNum) {
        switch(sectionNum)
        {
            case 1:
                return section1;
            case 2:
                return section2;
            case 3:
                return section3;
            case 4:
                return section4;
            default:
                return bossSection;
        }
    }

    public void clearSection(int sectionNum)
    {
        Section s = getSection(sectionNum);
        s.complete();
    }

    public void revealNextSection(int currentNum) {
        if(currentNum == AdventureConstants.SECTIONS_PER_WORLD)
            return;
        Section s = getSection(currentNum+1);
        s.reveal();
    }

    public int getWorldNum()
    {
        return worldNum;
    }

    public int numClearedSections() {
        int clearedSections = 0;
        if(section1.isCompleted())
            clearedSections++;
        if(section2.isCompleted())
            clearedSections++;
        if(section3.isCompleted())
            clearedSections++;
        if(section4.isCompleted())
            clearedSections++;
        return clearedSections;
    }

    public List<Section> getSections() {
        List<Section> sections = new ArrayList<>();
        sections.add(section1);
        sections.add(section2);
        sections.add(section3);
        sections.add(section4);
        sections.add(bossSection);
        return sections;
    }

    public void updateSection(AdvLocation advLocation, int sectionNum) {
        Section s = getSection(sectionNum);
        s.changeLocation(advLocation);
    }
    public boolean isBossRevealed() {
        return bossRevealed;
    }

    public int getMatchCount()
    {
        return worldStatTracker.getNumMatches();
    }

    public int getNumMatchType(AdvMatchResult m) {
        return worldStatTracker.getNumMatchType(m);
    }

    public void updateWorldStats(AdvMatchResult r) {
        worldStatTracker.incrementNumMatches(r);
    }

    public int calculateMookBonus() {
        return bonusCalculator.calculateMook(worldNum);
    }

    public int calculateBossBonus()
    {
        return bonusCalculator.calculateBoss(worldNum);
    }


    public void setBossRevealed(boolean b) {
        bossRevealed = b;
        logger.info("World Boss revealed to be "+ getBoss());
    }

    public Enemy enemyEscapes(int sectionNum) {
        return getSection(sectionNum).enemyEscapes();
    }

    public Section getRandomSection() {
        Random random = new Random();
        int sectionNum = random.nextInt(getSections().size())+1;
        return getSection(sectionNum);
    }

    public ActiveCardList retrieveStationedCards() {
        ActiveCardList stationedCards = new ActiveCardList();
        for(Section s: getSections())
        {
            stationedCards.addAll(s.unstationCards());
        }
        return stationedCards;
    }

    public boolean isCurrentSection(Section subject) {
        return currentSectionNum == subject.getSectionNum();
    }

    public void incrementCurrentSectionNum() {
        if (currentSectionNum == AdventureConstants.SECTIONS_PER_WORLD)
            currentSectionNum = 1;
        else
            currentSectionNum++;
    }

    public int getCurrentSectionNum() {
        return currentSectionNum;
    }
}
