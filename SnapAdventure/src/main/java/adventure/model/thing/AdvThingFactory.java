package adventure.model.thing;

import snapMain.model.database.TargetDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.Location;

import java.util.List;

public class AdvThingFactory {
    public TargetDatabase<AdvCard> loadBosses(TargetDatabase<Card> cards) {
        TargetDatabase<AdvCard> advCards = new TargetDatabase<>();
        AdvCardLoader vLoader = new AdvCardLoader();
        List<String[]> csvContents = vLoader.readCSV();
        //Add the boss to the list if it does not exist. Otherwise,
        for(int i = 0; i < cards.size(); i++)
        {
            String[] vInfo;
            Card c = cards.get(i);
            AdvCard b = new AdvCard(c);
            if(i < csvContents.size()) {
                vInfo = csvContents.get(i);
                b.fromSaveStringArray(vInfo);
            }
            c.setEnabled(b.isEnabled());
            advCards.add(b);
        }
        Mook m = new Mook();
        if(!advCards.contains(m))
            advCards.add(m);
        return advCards;
    }

    public TargetDatabase<AdvLocation> loadSections(TargetDatabase<Location> locations)
    {
        AdvLocationLoader sLoader = new AdvLocationLoader();
        List<String[]> csvContents = sLoader.readCSV();
        TargetDatabase<AdvLocation> advLocations = new TargetDatabase<>();
        for(int i = 0; i < locations.size(); i++)
        {
            String[] vInfo;
            Location l = locations.get(i);
            AdvLocation s = new AdvLocation(l);
            if(i < csvContents.size()) {
                vInfo = csvContents.get(i);
                s.fromSaveStringArray(vInfo);
            }
            l.setEnabled(s.isEnabled());
            advLocations.add(s);
        }
        Ruins r = new Ruins();
        if(!advLocations.contains(r))
            advLocations.add(r);
        return advLocations;
    }
}
