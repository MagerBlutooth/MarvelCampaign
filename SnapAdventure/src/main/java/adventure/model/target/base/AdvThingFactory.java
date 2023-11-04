package adventure.model.target.base;

import snapMain.model.database.TargetDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.Location;
import snapMain.model.target.Token;

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
                b.fromCSVSaveStringArray(vInfo);
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
                s.fromCSVSaveStringArray(vInfo);
            }
            l.setEnabled(s.isEnabled());
            advLocations.add(s);
        }
        Ruins r = new Ruins();
        if(!advLocations.contains(r))
            advLocations.add(r);
        return advLocations;
    }

    public TargetDatabase<AdvToken> loadAdvTokens(TargetDatabase<Token> tokens) {
        AdvTokenLoader sLoader = new AdvTokenLoader();
        List<String[]> csvContents = sLoader.readCSV();
        TargetDatabase<AdvToken> advTokens = new TargetDatabase<>();
        for(int i = 0; i < tokens.size(); i++)
        {
            String[] vInfo;
            Token t = tokens.get(i);
            AdvToken a = new AdvToken(t);
            if(i < csvContents.size()) {
                vInfo = csvContents.get(i);
                a.fromCSVSaveStringArray(vInfo);
            }
            t.setEnabled(a.isEnabled());
            advTokens.add(a);
        }
        BlankToken b = new BlankToken();
        if(!advTokens.contains(b))
            advTokens.add(b);
        return advTokens;
    }
}
