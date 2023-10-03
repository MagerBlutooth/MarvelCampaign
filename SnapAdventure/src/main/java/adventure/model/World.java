package adventure.model;

import adventure.model.thing.AdvCard;
import adventure.model.thing.AdvLocation;
import adventure.model.thing.Boss;
import adventure.model.thing.Section;
import snapMain.model.constants.CampaignConstants;
import snapMain.model.database.PlayableDatabase;
import snapMain.model.target.TargetType;

import java.util.Base64;
import java.util.List;
import java.util.Random;

public class World implements Cloneable{

    Section section1;
    Section section2;
    Section section3;
    Section section4;
    Boss boss;
    AdventureDatabase database;
    int worldNum;

    public World(AdventureDatabase db)
    {
        database = db;
    }

    public World(AdventureDatabase db, List<AdvLocation> locations, AdvCard b, int wNum)
    {
        database = db;
        worldNum = wNum;
        PlayableDatabase pD = db.getCardsAndTokens();
        section1 = new Section(1, locations.get(0), pD);
        section2 = new Section(2, locations.get(1), pD);
        section3 = new Section(3, locations.get(2), pD);
        section4 = new Section(4, locations.get(3), pD);
        boss = new Boss(b, 0);
        section1.reveal();
    }

    public World(World world) {
        database = world.database;
        section1 = world.section1;
        section2 = world.section2;
        section3 = world.section3;
        section4 = world.section4;
        boss = world.boss;
    }

    public String toSaveString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(worldNum);
        stringBuilder.append(CampaignConstants.CATEGORY_SEPARATOR);
        stringBuilder.append(section1.toSaveString());
        stringBuilder.append(CampaignConstants.CATEGORY_SEPARATOR);
        stringBuilder.append(section2.toSaveString());
        stringBuilder.append(CampaignConstants.CATEGORY_SEPARATOR);
        stringBuilder.append(section3.toSaveString());
        stringBuilder.append(CampaignConstants.CATEGORY_SEPARATOR);
        stringBuilder.append(section4.toSaveString());
        stringBuilder.append(CampaignConstants.CATEGORY_SEPARATOR);
        stringBuilder.append(boss.toSaveString());
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String saveString, AdvMainDatabase dB) {
        byte[] decodedBytes = Base64.getDecoder().decode(saveString);
        String decodedString = new String(decodedBytes);
        if(decodedString.isBlank())
            return;
        String[] stringList = decodedString.split(CampaignConstants.CATEGORY_SEPARATOR);
        worldNum = Integer.parseInt(stringList[0]);
        section1 = new Section(1);
        section1.fromSaveString(stringList[1].trim(), dB.lookupDatabase(TargetType.LOCATION));
        section2 = new Section(2);
        section2.fromSaveString(stringList[2].trim(), dB.lookupDatabase(TargetType.LOCATION));
        section3 = new Section(3);
        section3.fromSaveString(stringList[3].trim(), dB.lookupDatabase(TargetType.LOCATION));
        section4 = new Section(4);
        section4.fromSaveString(stringList[4].trim(), dB.lookupDatabase(TargetType.LOCATION));
        boss = new Boss();
        boss.fromSaveString(stringList[5].trim(), dB.lookupDatabase(TargetType.ADV_CARD));
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

    public Boss getBoss() {
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

    public void revealBoss()
    {
        boss.reveal();
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
}
