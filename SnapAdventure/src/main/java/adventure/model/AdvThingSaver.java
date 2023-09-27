package adventure.model;

import campaign.model.database.*;
import campaign.model.thing.Card;
import campaign.model.thing.Location;
import campaign.model.thing.ThingType;
import campaign.model.thing.Token;

public class AdvThingSaver {

    public void saveBosses(ThingDatabase<Boss> bosses)
    {
        BossSaver vSaver = new BossSaver(bosses);
        vSaver.writeCSV();
    }

    public void saveSections(ThingDatabase<Section> sections)
    {
        SectionSaver vSaver = new SectionSaver(sections);
        vSaver.writeCSV();
    }

    public void saveDatabase(ThingType type, AdvMasterThingDatabase db) {
        switch(type)
        {
            case CARD:
                saveBosses(db.getBosses());
            case LOCATION:
                saveSections(db.getSections());
        }
    }
}
