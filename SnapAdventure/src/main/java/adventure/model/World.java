package adventure.model;

import adventure.model.thing.AdvCard;
import adventure.model.thing.AdvLocation;
import adventure.model.thing.Enemy;
import adventure.model.thing.Section;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.PlayableDatabase;
import snapMain.model.target.TargetType;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

public class World implements Cloneable{

    Section section1;
    Section section2;
    Section section3;
    Section section4;
    Enemy boss;
    AdventureDatabase database;
    int worldNum;
    WorldBonusCalculator bonusCalculator;
    boolean bossRevealed;

    public World(AdventureDatabase db)
    {
        database = db;
        bonusCalculator = new WorldBonusCalculator();
    }

    public World(AdventureDatabase db, List<AdvLocation> locations, AdvCard b, int wNum)
    {
        database = db;
        bonusCalculator = new WorldBonusCalculator();
        worldNum = wNum;
        section1 = new Section(1, locations.get(0), db, new Enemy(worldNum));
        section2 = new Section(2, locations.get(1), db, new Enemy(worldNum));
        section3 = new Section(3, locations.get(2), db, new Enemy(worldNum));
        section4 = new Section(4, locations.get(3), db, new Enemy(worldNum));
        boss = new Enemy(b, getWorldBonus());
        section1.reveal();
    }

    public World(World world) {
        database = world.database;
        section1 = world.section1;
        section2 = world.section2;
        section3 = world.section3;
        section4 = world.section4;
        boss = world.boss;
        bonusCalculator = new WorldBonusCalculator();
    }

    public String toSaveString() {
        String result = worldNum +
                SnapMainConstants.CATEGORY_SEPARATOR +
                section1.toSaveString() +
                SnapMainConstants.CATEGORY_SEPARATOR +
                section2.toSaveString() +
                SnapMainConstants.CATEGORY_SEPARATOR +
                section3.toSaveString() +
                SnapMainConstants.CATEGORY_SEPARATOR +
                section4.toSaveString() +
                SnapMainConstants.CATEGORY_SEPARATOR +
                boss.toSaveString() +
                SnapMainConstants.CATEGORY_SEPARATOR +
                bossRevealed;
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String saveString, AdvMainDatabase dB) {
        byte[] decodedBytes = Base64.getDecoder().decode(saveString);
        String decodedString = new String(decodedBytes);
        if(decodedString.isBlank())
            return;
        String[] stringList = decodedString.split(SnapMainConstants.CATEGORY_SEPARATOR);
        worldNum = Integer.parseInt(stringList[0]);
        int worldBonus = bonusCalculator.calculateMook(worldNum);
        section1 = new Section(1, database, new Enemy(worldBonus));
        section1.fromSaveString(stringList[1].trim(), dB.lookupDatabase(TargetType.LOCATION));
        section2 = new Section(2, database, new Enemy(worldBonus));
        section2.fromSaveString(stringList[2].trim(), dB.lookupDatabase(TargetType.LOCATION));
        section3 = new Section(3, database, new Enemy(worldBonus));
        section3.fromSaveString(stringList[3].trim(), dB.lookupDatabase(TargetType.LOCATION));
        section4 = new Section(4, database, new Enemy(worldBonus));
        section4.fromSaveString(stringList[4].trim(), dB.lookupDatabase(TargetType.LOCATION));
        boss = new Enemy();
        boss.fromSaveString(stringList[5].trim(), dB);
        bossRevealed = Boolean.parseBoolean(stringList[6]);
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
        return boss;
    }

    public Section getRandomSection() {
        Random random = new Random();
        int sectionNum = random.nextInt(4)+1;
        return getSection(sectionNum);
    }

    public Section getSection(int sectionNum) {
        switch(sectionNum)
        {
            case 2:
                return section2;
            case 3:
                return section3;
            case 4:
                return section4;
            default:
                return section1;
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
        return sections;
    }

    public void updateSection(AdvLocation advLocation, int sectionNum) {
        Section s = getSection(sectionNum);
        s.changeLocation(advLocation);
    }

    private int getWorldBonus() {
        if(worldNum < 2)
        {
            return 0;
        }
        if(worldNum < 4)
        {
            return 1;
        }
        if(worldNum < 6)
        {
            return 2;
        }
        if(worldNum < 8)
        {
            return 3;
        }
        return 5;
    }

    public void revealBoss() {
        bossRevealed = true;
    }

    public boolean isBossRevealed() {
        return bossRevealed;
    }
}
