package model.thing;

import model.constants.CampaignConstants;
import model.database.CampaignDatabase;
import model.database.FactionLabel;
import model.database.ThingDatabase;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static model.constants.CampaignConstants.*;

public class Faction {


    private int medbayId;
    String name;
    FactionLabel factionLabel;
    CardList ownedAgents;
    LocationList ownedLocations;
    CardList unownedAgents;
    LocationList unownedLocations;
    CardList enemyAgents;
    LocationList enemyLocations;
    CardList eliminatedAgents;
    CardList capturedAgents;
    Location medbay;

    //Constructor used to construct Faction using individual components
    public Faction(FactionLabel f, CardList a, LocationList locs, CampaignDatabase database)
    {
        name = f.toString();
        factionLabel = f;
        setMedBayID();
        ownedAgents = a;
        ownedLocations = locs;
        unownedAgents = new CardList(database.getCards());
        unownedAgents.removeAll(ownedAgents.getCards());
        unownedLocations = new LocationList(database.getLocations());
        unownedLocations.removeAll(ownedLocations.getLocations());
        enemyAgents = new CardList(new ArrayList<>());
        enemyLocations = new LocationList(new ArrayList<>());
        eliminatedAgents = new CardList(new ArrayList<>());
        setMedbay(database);

    }

    //Constructor used to load in Faction using the database and a Base64 encoded string
    public Faction(String encodedString, CampaignDatabase database)
    {
        ownedAgents = new CardList(new ArrayList<>());
        ownedLocations = new LocationList(new ArrayList<>());
        eliminatedAgents = new CardList(new ArrayList<>());
        fromSaveString(encodedString, database);

        //TODO: Add Unowned+Enemy Agents/Locations to encoded string, since these are maintained by the user
        unownedAgents = new CardList(database.getCards());
        unownedAgents.removeAll(ownedAgents.getCards());
        unownedLocations = new LocationList(database.getLocations());
        unownedLocations.removeAll(ownedLocations.getLocations());
        enemyAgents = new CardList(new ArrayList<>());
        enemyLocations = new LocationList(new ArrayList<>());
        setMedbay(database);

    }

    //The Medbay is added to owned locations when saving the Location List from the Player Side. This method
    //instead sets it as the dedicated Medbay value and removes it from owned Locations
    // while preserving the stationed Agent(s) inside.
    private void setMedbay(CampaignDatabase database) {
        int medbayIndex = ownedLocations.lookupId(medbayId);
        if (medbayIndex >= 0) {
            medbay = ownedLocations.get(medbayIndex);
            ownedLocations.remove(ownedLocations.get(medbayIndex));
        } else {
           if(factionLabel == FactionLabel.SHIELD)
                medbay = new Location(database.getShieldMedbay());
           else
               medbay = new Location(database.getHydraMedbay());
        }
    }

    public CardList getUnownedAgents()
    {
        return unownedAgents;
    }

    public LocationList getEnemyLocations()
    {
        return enemyLocations;
    }

    public LocationList getUnownedLocations()
    {
        return unownedLocations;
    }

    public CardList getOwnedAgents() {
        return ownedAgents;
    }

    public List<Card> getAgentCards()
    {
        return ownedAgents.getCards();
    }

    public LocationList getOwnedLocations()
    {
        return ownedLocations;
    }
    public LocationList getOwnedLocationsAndMedbay()
    {
        LocationList ownedLocs = new LocationList(new ArrayList<>());
        ownedLocs.add(medbay);
        ownedLocs.addAll(ownedLocations.getLocations());
        return ownedLocs;
    }

    public String getName() {
        return name;
    }

    public CardList getCaptains() {
        CardList captains = new CardList(new ArrayList<>());
        for(Card a: getAgentCards())
        {
            if(a.isCaptain())
                captains.add(a);
        }
        return captains;
    }

    public CardList getAllOwnedAgents()
    {
        return ownedAgents;
    }

    public CardList getStationedAgents()
    {
        CardList stationedAgents = new CardList(new ArrayList<>());
        for(Location l: ownedLocations)
        {
            stationedAgents.addAll(l.getStationedAgents().getThings());
        }
        return stationedAgents;
    }

    public CardList getAgentsInHQ()
    {
        CardList hqAgents = new CardList(getAllOwnedAgents());
        hqAgents.removeAll(getStationedAgents().getCards());
        return hqAgents;
    }

    public String toString()
    {
        return name;
    }

    public String toSaveString()
    {
        String nString = name;
        StringBuilder aString = new StringBuilder();
        StringBuilder lString = new StringBuilder();
        StringBuilder eString = new StringBuilder();

        //Construct Agents string
        for(int i = 0; i < ownedAgents.size(); i++)
        {
            Card a = ownedAgents.get(i);
            aString.append(a.getID());
            aString.append(SUBCATEGORY_SEPARATOR);
            aString.append(a.isWounded());
            aString.append(SUBCATEGORY_SEPARATOR);
            aString.append(a.isCaptain());
            aString.append(STRING_SEPARATOR);
        }

        //Construct Locations string
        for(int i = 0; i < getOwnedLocationsAndMedbay().size(); i++)
        {
            Location l = getOwnedLocationsAndMedbay().get(i);
            lString.append(l.getID()).append(STRING_SEPARATOR);
        }

        //Construct Eliminated Agents string
        for(int i = 0; i < eliminatedAgents.size(); i++)
        {
            Card e = eliminatedAgents.get(i);
            eString.append(e.getID()).append(STRING_SEPARATOR);
        }

        //Construct and encode Full string
        String resultString = nString + CATEGORY_SEPARATOR +
                aString + CATEGORY_SEPARATOR +
                lString + CATEGORY_SEPARATOR +
                eString;
        return Base64.getEncoder().encodeToString(resultString.getBytes());
    }

    public void fromSaveString(String encodedString, CampaignDatabase database) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String s = new String(decodedBytes);
        String[] stringCats = s.split(CATEGORY_SEPARATOR);
        ThingDatabase<Card> cDatabase = database.getCards();
        ThingDatabase<Location> lDatabase = database.getLocations();
        name = stringCats[0];
        factionLabel = FactionLabel.valueOf(name.toUpperCase());
        setMedBayID();
        //Lookup Agents
        for (String a : stringCats[1].split(STRING_SEPARATOR))
        {
            String[] cardString = a.split(SUBCATEGORY_SEPARATOR);
            Card c = new Card(cDatabase.lookup(Integer.parseInt(cardString[0])));
            c.setWounded(Boolean.parseBoolean(cardString[1]));
            c.setCaptain(Boolean.parseBoolean(cardString[2]));
            ownedAgents.add(c);
        }
        //Lookup Locations
        for (String b : stringCats[2].split(STRING_SEPARATOR))
        {
            Location l;
            if(Integer.parseInt(b) == medbayId)
                l = new Location(database.getMedbay(factionLabel));
            else
                l = new Location(lDatabase.lookup(Integer.parseInt(b)));
            ownedLocations.add(l);
        }
        if(stringCats.length == 4) {
            //Lookup Eliminated Agents
            for (String c : stringCats[3].split(STRING_SEPARATOR)) {
                Card e = new Card(cDatabase.lookup(Integer.parseInt(c)));
                eliminatedAgents.add(e);
            }
        }
    }

    private void setMedBayID() {
        if(factionLabel == FactionLabel.SHIELD)
            medbayId = SHIELD_MEDBAY_ID;
        else
            medbayId = HYDRA_MEDBAY_ID;

    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Faction))
            return false;
        return ((Faction)o).getName().equals(getName());
    }

    public boolean ownsThing(Thing t) {
        if(t instanceof Card)
            return ownedAgents.contains((Card)t);
        if(t instanceof Location)
            return ownedLocations.contains((Location)t);
        return false;
    }

    public List<Location> getLocationObjects() {
        return ownedLocations.getLocations();
    }

    public CardList getGrave() {
        return eliminatedAgents;
    }

    public void eliminateAgent(Card card) {
        eliminatedAgents.add(card);
        ownedAgents.remove(card);
    }

    public void reviveAgent(Card card) {
        ownedAgents.add(card);
        eliminatedAgents.remove(card);
    }

    public void clearStationedAgents() {
        ownedLocations.clearStationedAgents();
        medbay.clearStationedAgents();
    }

    public void stationAgent(Card c, Location l) {
        if(l.equals(medbay))
            medbay.stationAgent(c);
        else
            ownedLocations.stationAgent(l, c);

    }

    public void removeStationedAgent(Card c) {
        ownedLocations.removeStationedAgent(c);
        medbay.removeStationedAgent(c);
    }

    public FactionLabel getFactionLabel()
    {
        return factionLabel;
    }

    public <T extends Thing> void addThing(T thing) {
        if (thing instanceof Card) {
            ownedAgents.add((Card)thing);
            unownedAgents.remove((Card)thing);
        }
        else if(thing instanceof Location)
        {
            ownedLocations.add((Location)thing);
            unownedLocations.remove((Location)thing);
        }
    }

    public void removeThing(Thing thing) {
        if (thing instanceof Card) {
            ownedAgents.remove((Card)thing);
            unownedAgents.add((Card)thing);
        }
        else if(thing instanceof Location)
        {
            ownedLocations.remove((Location)thing);
            unownedLocations.add((Location)thing);
        }
    }

    public ThingList<Location> getActiveLocationsAndMedbay() {
        ThingList<Location> ownedLocationsAndMedbay = getOwnedLocationsAndMedbay();
        ThingList<Location> activeLocations = new LocationList(new ArrayList<>());
        for(Location l: ownedLocationsAndMedbay)
        {
            if(!l.isRuined())
                activeLocations.add(l);
        }
        return activeLocations;
    }
}
