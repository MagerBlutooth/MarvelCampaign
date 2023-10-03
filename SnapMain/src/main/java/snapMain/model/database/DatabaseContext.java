package snapMain.model.database;

import snapMain.model.target.BaseObject;
import snapMain.model.target.TargetType;

import java.util.LinkedHashMap;

public class DatabaseContext {

    LinkedHashMap<TargetType, TargetDatabase> context = new LinkedHashMap<>();

    public <T extends BaseObject> void register(TargetType t, TargetDatabase<T> dB)
    {
        context.putIfAbsent(t, dB);
    }

    public TargetDatabase lookup(TargetType l) {
        return context.get(l);
    }
}
