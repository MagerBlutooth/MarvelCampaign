package controller.node;

import model.database.MasterThingDatabase;
import model.thing.Thing;

public abstract class CampaignListNodeController<T extends Thing>{

    MasterThingDatabase database;

    public void initialize(MasterThingDatabase d) {
        database = d;
    }
}
