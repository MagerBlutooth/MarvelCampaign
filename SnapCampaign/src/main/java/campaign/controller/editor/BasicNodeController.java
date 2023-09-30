package campaign.controller.editor;

import campaign.controller.MainDatabase;
import campaign.model.thing.Thing;

public abstract class BasicNodeController<C extends MainDatabase, T extends Thing>{

    C database;
    Thing subject;

    public void initialize(C db, T t) {
        database = db;
        subject = t;
    }
}
