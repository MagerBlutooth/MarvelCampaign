package view;

import model.database.MasterThingDatabase;
import model.thing.Card;
import model.thing.Location;
import model.thing.ThingType;
import model.thing.Token;
import view.grabber.ThingImageGrabber;

import java.util.LinkedHashMap;
import java.util.Map;

import static model.constants.CampaignConstants.NO_ICON_ID;

public class CampaignImageCache {

    Map<Integer, IconImage> cardImageCache = new LinkedHashMap<>();
    Map<Integer, IconImage> locationImageCache = new LinkedHashMap<>();
    Map<Integer, IconImage> tokenImageCache = new LinkedHashMap<>();

    MasterThingDatabase masterThingDatabase;

    ThingImageGrabber cardImageGrabber = new ThingImageGrabber(ThingType.CARD);
    ThingImageGrabber locationImageGrabber = new ThingImageGrabber(ThingType.LOCATION);
    ThingImageGrabber tokenImageGrabber = new ThingImageGrabber(ThingType.TOKEN);


    public CampaignImageCache(MasterThingDatabase database)
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
    }

    private void setCardCache() {
        for(Card c: masterThingDatabase.getCards())
        {
            cardImageCache.put(c.getID(), cardImageGrabber.grabImage(c.getID()));
        }
        cardImageCache.put(NO_ICON_ID, cardImageGrabber.grabImage(NO_ICON_ID));
    }

    public IconImage getImage(int id, ThingType tt) {
        switch(tt)
        {
            case CARD:
                return cardImageCache.get(id);
            case LOCATION:
                return locationImageCache.get(id);
            case TOKEN:
                return tokenImageCache.get(id);
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