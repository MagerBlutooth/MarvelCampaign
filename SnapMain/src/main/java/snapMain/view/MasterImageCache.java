package snapMain.view;

import snapMain.model.database.MasterThingDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.Location;
import snapMain.model.target.TargetType;
import snapMain.model.target.Token;
import snapMain.view.grabber.ThingImageGrabber;

import java.util.LinkedHashMap;
import java.util.Map;

import static snapMain.model.constants.SnapMainConstants.*;

public class MasterImageCache {

    Map<Integer, IconImage> cardImageCache = new LinkedHashMap<>();
    Map<Integer, IconImage> locationImageCache = new LinkedHashMap<>();
    Map<Integer, IconImage> tokenImageCache = new LinkedHashMap<>();

    MasterThingDatabase masterThingDatabase;

    ThingImageGrabber cardImageGrabber = new ThingImageGrabber(TargetType.CARD);
    ThingImageGrabber locationImageGrabber = new ThingImageGrabber(TargetType.LOCATION);
    ThingImageGrabber tokenImageGrabber = new ThingImageGrabber(TargetType.TOKEN);


    public MasterImageCache(MasterThingDatabase database)
    {
        masterThingDatabase = database;
        setCardCache();
        setLocationCache();
        setTokenCache();
    }

    private void setTokenCache() {

        for(Token t: masterThingDatabase.getTokens())
        {
            tokenImageCache.put(t.getID(), tokenImageGrabber.grabImage(t.getID()));
        }
        tokenImageCache.put(NO_ICON_ID, tokenImageGrabber.grabImage(NO_ICON_ID));
    }

    private void setLocationCache() {
        for(Location l: masterThingDatabase.getLocationsAndMedbay())
        {
            locationImageCache.put(l.getID(), locationImageGrabber.grabImage(l.getID()));
        }
        locationImageCache.put(NO_ICON_ID, locationImageGrabber.grabImage(NO_ICON_ID));
        locationImageCache.put(RUINS_ICON_ID, locationImageGrabber.grabImage(RUINS_ICON_ID));

    }

    private void setCardCache() {
        for(Card c: masterThingDatabase.getCards())
        {
            cardImageCache.put(c.getID(), cardImageGrabber.grabImage(c.getID()));
        }
        cardImageCache.put(NO_ICON_ID, cardImageGrabber.grabImage(NO_ICON_ID));
        cardImageCache.put(MOOK_ICON_ID, cardImageGrabber.grabImage(MOOK_ICON_ID));
    }

    public IconImage getImage(int id, TargetType tt) {
        switch(tt)
        {
            case CARD:
                return cardImageCache.get(id);
            case LOCATION:
                return locationImageCache.get(id);
            case TOKEN:
                return tokenImageCache.get(id);
            case CARD_OR_TOKEN:

        }
        return null;
    }

    public void cacheCard(Card c, IconImage i) {
        cardImageCache.put(c.getID(), i);
    }

    public void cacheLocation(Location l, IconImage i)
    {
        locationImageCache.put(l.getID(), i);
    }

    public void cacheToken(Token t, IconImage i)
    {
        tokenImageCache.put(t.getID(), i);
    }
}