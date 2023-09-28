package adventure.model;

import campaign.model.database.ThingDatabase;
import campaign.model.thing.Card;

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

    public ThingDatabase<Boss> getBosses() {
        return bosses;
    }

    public ThingDatabase<Section> getSections() {
        return sections;
    }

    public ThingDatabase<Card> getCards() {
        return cards;
    }
}
