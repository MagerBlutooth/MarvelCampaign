package model.database;

import model.thing.Thing;
import model.thing.ThingType;

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
