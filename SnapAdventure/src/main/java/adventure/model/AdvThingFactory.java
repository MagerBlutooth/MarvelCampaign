package adventure.model;

import campaign.model.database.CardLoader;
import campaign.model.database.LocationLoader;
import campaign.model.database.ThingDatabase;
import campaign.model.database.TokenLoader;
import campaign.model.thing.Card;
import campaign.model.thing.Location;
import campaign.model.thing.Token;

import java.util.List;

public class AdvThingFactory {
    public ThingDatabase<Boss> loadBosses(ThingDatabase<Card> cards) {
        ThingDatabase<Boss> bosses = new ThingDatabase<>();
        BossLoader vLoader = new BossLoader();
        List<String[]> csvContents = vLoader.readCSV();
        //Add the boss to the list if it does not exist. Otherwise,
        for(int i = 0; i < cards.size(); i++)
        {
            String[] vInfo;
            Card c = cards.get(i);
            Boss b = new Boss(c);
            if(i < csvContents.size()) {
                vInfo = csvContents.get(i);
                b.fromSaveStringArray(vInfo);
            }
            c.setEnabled(b.isEnabled());
            bosses.add(b);
        }
        return bosses;
    }

    public ThingDatabase<Section> loadSections(ThingDatabase<Location> locations)
    {
        SectionLoader sLoader = new SectionLoader();
        List<String[]> csvContents = sLoader.readCSV();
        ThingDatabase<Section> sections = new ThingDatabase<>();
        for(int i = 0; i < locations.size(); i++)
        {
            String[] vInfo;
            Location l = locations.get(i);
            Section s = new Section(l);
            if(i < csvContents.size()) {
                vInfo = csvContents.get(i);
                s.fromSaveStringArray(vInfo);
            }
            l.setEnabled(s.isEnabled());
            sections.add(s);
        }
        return sections;
    }
}
