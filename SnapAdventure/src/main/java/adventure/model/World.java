package adventure.model;

import campaign.model.thing.Thing;
import campaign.model.thing.ThingType;

import java.util.List;

public class World extends Thing {
    Section section1;
    Section section2;
    Section section3;
    Section section4;
    Boss boss;
    AdventureDatabase database;
    int currentSectionNum;

    public World(AdventureDatabase db)
    {
        database = db;
    }

    public World(AdventureDatabase db, Section s1, Section s2, Section s3, Section s4, Boss b)
    {
        database = db;
        section1 = s1;
        section2 = s2;
        section3 = s3;
        section4 = s4;
        boss = b;
        currentSectionNum = 1;
    }

    public World(World world) {
        database = world.database;
        section1 = world.section1;
        section2 = world.section2;
        section3 = world.section3;
        section4 = world.section4;
        boss = world.boss;
        currentSectionNum = world.getCurrentSectionNum();
    }

    @Override
    public String[] toSaveStringArray() {
        return new String[]{section1.getID()+"", section2.getID()+"", section3.getID()+"", section4.getID()+"", boss.getID()+""};
    }

    @Override
    public void fromSaveStringArray(String[] mInfo) {
        List<Section> dbSections = database.getSections();
        List<Boss> dbBosses = database.getBosses();
        section1 = dbSections.get(Integer.parseInt(mInfo[0]));
        section2 = dbSections.get(Integer.parseInt(mInfo[1]));
        section3 = dbSections.get(Integer.parseInt(mInfo[2]));
        section4 = dbSections.get(Integer.parseInt(mInfo[3]));
        boss = dbBosses.get(Integer.parseInt(mInfo[4]));
    }

    @Override
    public ThingType getThingType() {
        return null;
    }

    @Override
    public boolean hasAttribute(String att) {
        return false;
    }

    @Override
    public World clone() {
        return new World(this);
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

    public Section getCurrentSection() {
        switch(currentSectionNum)
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

    public int getCurrentSectionNum() {
        return currentSectionNum;
    }
    public void setCurrentSectionNum(int cs)
    {
        currentSectionNum = cs;
    }
}
