package model.thing;

import model.database.MasterThingDatabase;
import model.database.ThingDatabase;
import model.sortFilter.LocationSortMode;
import model.sortFilter.LocationSorter;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static model.constants.CampaignConstants.CATEGORY_SEPARATOR;
import static model.constants.CampaignConstants.STRING_SEPARATOR;

public class LocationList extends ThingList<Location> {

    LocationSorter locationSorter = new LocationSorter();

    public LocationList(List<Location> locs)
    {
        super(locs);
    }

    public void sort()
    {
        List<Location> sortedLocations = locationSorter.sort(new ArrayList<>(getLocations()));
        this.clear();
        this.addAll(sortedLocations);
    }

    public void setSortMode(String m) {
        locationSorter.changeMode(LocationSortMode.parseString(m));
    }
    public void setSortMode(LocationSortMode l) {
        locationSorter.changeMode(l);
    }

    public List<Location> getLocations() {
        return getThings();
    }

    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(Location l: getLocations())
        {
            stringBuilder.append(l.getID());
                for (Card c : l.getStationedAgents()) {
                    stringBuilder.append(STRING_SEPARATOR);
                    stringBuilder.append(c.getID());
                }
            stringBuilder.append(CATEGORY_SEPARATOR);
        }
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromString(String locationString, MasterThingDatabase database)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(locationString);
        String decodedString = new String(decodedBytes);
        String[] locList = decodedString.split(CATEGORY_SEPARATOR);
        ThingDatabase<Location> locDatabase = database.getLocationsAndMedbay();
        ThingDatabase<Card> cardDatabase = database.getCards();
        for(String c: locList)
        {
            String[] stationedAgents = c.split(STRING_SEPARATOR);
            Location l = new Location(locDatabase.lookup(Integer.parseInt(stationedAgents[0])));
            l.removeStationedAgents();
            for(int i = 1; i < stationedAgents.length; i++)
            {
                Card agent = new Card(cardDatabase.lookup(Integer.parseInt(stationedAgents[i])));
                l.stationAgent(agent);
            }
            this.add(l);
        }
    }

    public int lookupId(int id) {
        for(Location l: this)
        {
            if(l.getID() == id)
                return indexOf(l);
        }
        return -1;
    }

    public int indexOf(Location l) {
        return getThings().indexOf(l);
    }

    public void clearStationedAgents() {
        for(Location l: this)
        {
            l.removeStationedAgents();
        }
    }

    public void stationAgent(Location l, Card c) {
        l.stationAgent(c);
    }

    public int getStationedAgentCount() {
        int count = 0;
        for(Location l: this)
        {
            count += l.getStationedAgents().size();
        }
        return count;
    }

    public void removeStationedAgent(Card c) {
        for(Location l: this)
        {
            if(l.getStationedAgents().contains(c))
            {
                l.removeStationedAgent(c);
            }
        }
    }

    public String toListString() {
            StringBuilder stringBuilder = new StringBuilder();
            sort();
            for(Location l: getLocations())
            {
                stringBuilder.append(l.getName());
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }
}
