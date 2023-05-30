package controller.editor;

import controller.ControllerDatabase;
import model.thing.Thing;

public abstract class CampaignNodeController<T extends Thing>{

    ControllerDatabase database;
    Thing subject;

    public void initialize(ControllerDatabase d, T t) {
        database = d;
        subject = t;
    }
}
