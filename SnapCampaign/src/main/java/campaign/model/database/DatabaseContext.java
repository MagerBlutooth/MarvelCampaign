package campaign.model.database;

import campaign.model.thing.Thing;
import campaign.model.thing.ThingType;

import java.util.LinkedHashMap;

public class DatabaseContext {

    LinkedHashMap<ThingType, ThingDatabase> context = new LinkedHashMap<>();

    public <T extends Thing> void register(ThingType t, ThingDatabase<T> dB)
    {
        context.putIfAbsent(t, dB);
    }

    public ThingDatabase lookup(ThingType l) {
        return context.get(l);
    }
}
