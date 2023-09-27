package campaign.controller.editor;

import campaign.controller.ControllerDatabase;
import campaign.model.thing.Thing;

public abstract class BasicNodeController<C extends ControllerDatabase, T extends Thing>{

    C database;
    Thing subject;

    public void initialize(C db, T t) {
        database = db;
        subject = t;
    }
}
