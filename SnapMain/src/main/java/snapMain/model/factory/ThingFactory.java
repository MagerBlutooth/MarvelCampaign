package snapMain.model.factory;

import snapMain.model.database.*;
import snapMain.model.target.Card;
import snapMain.model.target.Location;
import snapMain.model.target.Token;

import java.util.List;

public class ThingFactory {
    public TargetDatabase<Card> loadCards() {
        CardLoader vLoader = new CardLoader();
        List<String[]> csvContents = vLoader.readCSV();
        TargetDatabase<Card> cards = new TargetDatabase<>();
        for(int i = 0; i < csvContents.size(); i++)
        {
            String[] vInfo = csvContents.get(i);
            Card c = new Card();
            c.fromSaveStringArray(vInfo);
            cards.add(c);
        }
        return cards;
    }

    public TargetDatabase<Location> loadLocations()
    {
        LocationLoader wLoader = new LocationLoader();
        List<String[]> csvContents = wLoader.readCSV();
        TargetDatabase<Location> worlds = new TargetDatabase<>();
        for(int i = 0; i < csvContents.size(); i++)
        {
            String[] vInfo = csvContents.get(i);
            Location l = new Location();
            l.fromSaveStringArray(vInfo);
            worlds.add(l);
        }
        return worlds;
    }

    public TargetDatabase<Token> loadTokens()
    {
        TokenLoader tLoader = new TokenLoader();
        List<String[]> csvContents = tLoader.readCSV();
        TargetDatabase<Token> tokens = new TargetDatabase<>();
        for(int i = 0; i < csvContents.size(); i++)
        {
            String[] vInfo = csvContents.get(i);
            Token t = new Token();
            t.fromSaveStringArray(vInfo);
            tokens.add(t);
        }
        return tokens;
    }
}
