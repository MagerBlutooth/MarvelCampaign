package adventure.model.target;

import snapMain.model.target.Playable;
import snapMain.model.target.TargetType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayableDatabase extends ArrayList<Playable> {

    //Numbers less than 0 reserved for special hardcoded images
    public Playable lookup(int id, TargetType type) {
        if(id >= 0) {
            for(Playable p : this)
            {
                if(p.getID() == id && p.getTargetType() == type)
                    return p;
            }
        }
        return null;
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

    public Playable getRandom() {
        List<Playable> items = new ArrayList<>(this);
        Collections.shuffle(items);
        return items.get(0);
    }

    public List<Playable> getRandom(int num)
    {
        List<Playable> items = new ArrayList<>(this);
        Collections.shuffle(items);
        return items.subList(0, num);
    }


}
