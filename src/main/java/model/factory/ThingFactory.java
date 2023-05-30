package model.factory;

import model.database.CardLoader;
import model.database.ThingDatabase;
import model.database.LocationLoader;
import model.database.TokenLoader;
import model.thing.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ThingFactory {
    public ThingDatabase<Card> loadCards() {
        CardLoader vLoader = new CardLoader();
        List<String[]> csvContents = vLoader.readCSV();
        ThingDatabase<Card> cards = new ThingDatabase<>();
        for(int i = 0; i < csvContents.size(); i++)
        {
            String[] vInfo = csvContents.get(i);
            Card c = new Card();
            c.fromSaveStringArray(vInfo);
            cards.add(c);
        }
        return cards;
    }

    public ThingDatabase<Location> loadLocations()
    {
        LocationLoader wLoader = new LocationLoader();
        List<String[]> csvContents = wLoader.readCSV();
        ThingDatabase<Location> worlds = new ThingDatabase<>();
        for(int i = 0; i < csvContents.size(); i++)
        {
            String[] vInfo = csvContents.get(i);
            Location l = new Location();
            l.fromSaveStringArray(vInfo);
            worlds.add(l);
        }
        return worlds;
    }

    public ThingDatabase<Token> loadTokens()
    {
        TokenLoader tLoader = new TokenLoader();
        List<String[]> csvContents = tLoader.readCSV();
        ThingDatabase<Token> tokens = new ThingDatabase<>();
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
