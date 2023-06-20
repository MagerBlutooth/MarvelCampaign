package model.sortFilter;

import model.thing.Card;
import model.thing.Location;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LocationSorter {

    LocationSortMode locationSortMode;

    public LocationSorter()
    {
        locationSortMode = LocationSortMode.NAME;
    }

    public List<Location> sort(List<Location> location)
    {
        List<Location> sortedLocs = new ArrayList<>(location);
        switch(locationSortMode)
        {
            //TODO: Add custom value to save a Map Position so Locations can have proximity to one another
            case MAP_POSITION:
                break;
            case NAME:
                sortedLocs.sort((o1, o2) -> Comparator.comparing(Location::getName).compare(o1, o2));
                break;
        }
        return sortedLocs;
    }

    public void changeMode(LocationSortMode c)
    {
        locationSortMode = c;
    }
}
