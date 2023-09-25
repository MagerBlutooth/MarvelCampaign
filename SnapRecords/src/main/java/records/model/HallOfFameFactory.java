package records.model;

import campaign.model.database.ThingDatabase;
import campaign.model.thing.Card;

import java.util.List;

public class HallOfFameFactory {

    public ThingDatabase<HallOfFameEntry> loadHallOfFame(ThingDatabase<Card> allCards)
    {
        HallOfFameLoader hLoader = new HallOfFameLoader();
        List<String[]> csvContents = hLoader.readCSV();
        ThingDatabase<HallOfFameEntry> entries = new ThingDatabase<>();
        for(int i = 0; i < csvContents.size(); i++)
        {
            String[] vInfo = csvContents.get(i);
            HallOfFameEntry e = new HallOfFameEntry(allCards);
            e.fromSaveStringArray(vInfo);
            entries.add(e);
        }
        return entries;
    }
}
