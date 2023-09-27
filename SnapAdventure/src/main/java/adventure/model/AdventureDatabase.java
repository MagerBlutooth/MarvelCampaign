package adventure.model;

import campaign.model.database.FactionLabel;
import campaign.model.database.MasterThingDatabase;
import campaign.model.database.ThingDatabase;
import campaign.model.thing.Card;
import campaign.model.thing.Location;
import campaign.model.thing.Token;

public class AdventureDatabase {

    ThingDatabase<Card> cards;
    ThingDatabase<Boss> bosses;
    ThingDatabase<Section> sections;
    public AdventureDatabase(AdvControllerDatabase controllerDatabase)
    {
        AdvMasterThingDatabase masterThingDatabase = controllerDatabase.getAdvMasterThingDatabase();
        cards = masterThingDatabase.getEnabledCards();
        bosses = masterThingDatabase.getEnabledBosses();
        sections = masterThingDatabase.getEnabledSections();
    }

    public ThingDatabase<Boss> getBoss() {
        return bosses;
    }

    public ThingDatabase<Section> getSections() {
        return sections;
    }
}
