package campaign.controller.editor;

import campaign.controller.ControllerDatabase;
import campaign.model.thing.Thing;

public abstract class CampaignNodeController<T extends Thing>{

    ControllerDatabase database;
    Thing subject;

    public void initialize(ControllerDatabase d, T t) {
        database = d;
        subject = t;
    }
}
