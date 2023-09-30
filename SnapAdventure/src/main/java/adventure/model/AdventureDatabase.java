package adventure.model;

import campaign.model.database.ThingDatabase;
import campaign.model.thing.Card;
import campaign.model.thing.CardList;
import campaign.model.thing.Thing;

import java.util.ArrayList;

public class AdventureDatabase {

    ThingDatabase<Card> cards;
    ThingDatabase<Boss> bosses;
    ThingDatabase<Section> sections;
    public AdventureDatabase(AdvMainDatabase controllerDatabase)
    {
        AdvMasterThingDatabase masterThingDatabase = controllerDatabase.getAdvMasterThingDatabase();
        CardList clonedCards = new CardList(new ArrayList<>());
        clonedCards = clonedCards.cloneNewList(masterThingDatabase.getEnabledCards());
        cards = new ThingDatabase<>();
        cards.addAll(clonedCards.getCards());
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
