package records.model;

import snapMain.model.database.TargetDatabase;
import snapMain.model.target.Card;

import java.util.List;

public class HallOfFameFactory {

    public TargetDatabase<HallOfFameEntry> loadHallOfFame(TargetDatabase<Card> allCards)
    {
        HallOfFameLoader hLoader = new HallOfFameLoader();
        List<String[]> csvContents = hLoader.readCSV();
        TargetDatabase<HallOfFameEntry> entries = new TargetDatabase<>();
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
