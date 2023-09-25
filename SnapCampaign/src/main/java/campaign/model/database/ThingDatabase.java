package campaign.model.database;

import campaign.model.thing.Thing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThingDatabase<T extends Thing> extends ArrayList<T> {


    //Numbers less than 0 reserved for special hardcoded images
    public T lookup(int id) {
        if(id >= 0) {
            for(T t: this)
            {
                if(t.getID() == id)
                    return t;
            }
        }
        return null;
    }
    public T lookup(String s)
    {
        for(T t: this)
        {
            if(t.getName().equals(s))
                return t;
        }
        return null;
    }

    public void addNewEntry(T newEntry) {
        int entryID = newEntry.getID();
        T l = lookup(entryID);
        if(l == null)
        {
            int id = getNextIDNum();
            newEntry.setID(id);
            this.add(newEntry);
        }
        else {
            this.set(indexOf(l), newEntry);
        }
        sortDatabase();
    }

    //Method to autosort the database after adding new cards, to keep cards in order of id.
    public void sortDatabase()
    {
        Collections.sort(this, (o1, o2) -> {
            if(o1.getID() < o2.getID())
                return 1;
            else if(o2.getID() > o1.getID())
                return -1;
            return 0;
        });
    }

    public int getNextIDNum()
    {
        return this.size();
    }

    public T getRandom() {
        List<T> items = new ArrayList<>(this);
        Collections.shuffle(items);
        return items.get(0);
    }

    public List<T> getRandom(int num)
    {
        List<T> items = new ArrayList<>(this);
        Collections.shuffle(items);
        return items.subList(0, num);
    }
}
