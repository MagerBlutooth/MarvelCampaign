package adventure.model;

import campaign.model.database.ThingDatabase;
import campaign.model.thing.Thing;
import campaign.model.thing.ThingType;

import java.util.List;

public class World implements Cloneable{
    Section section1;
    Section section2;
    Section section3;
    Section section4;
    Boss boss;
    AdventureDatabase database;

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
    }

    public World(World world) {
        database = world.database;
        section1 = world.section1;
        section2 = world.section2;
        section3 = world.section3;
        section4 = world.section4;
        boss = world.boss;
    }

    public String[] toSaveStringArray() {
        return new String[]{section1.getID()+"", section2.getID()+"", section3.getID()+"", section4.getID()+"", boss.getID()+""};
    }


    public void fromSaveStringArray(String[] mInfo) {
        ThingDatabase<Section> dbSections = database.getSections();
        ThingDatabase<Boss> dbBosses = database.getBosses();
        section1 = new Section(dbSections.lookup(Integer.parseInt(mInfo[0].trim())));
        section2 = new Section(dbSections.lookup(Integer.parseInt(mInfo[1].trim())));
        section3 = new Section(dbSections.lookup(Integer.parseInt(mInfo[2].trim())));
        section4 = new Section(dbSections.lookup(Integer.parseInt(mInfo[3].trim())));
        boss = new Boss(dbBosses.lookup(Integer.parseInt(mInfo[4].trim())));
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

    public Section getCurrentSection(int currentSection) {
        switch(currentSection)
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
}
