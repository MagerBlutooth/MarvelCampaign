package adventure.model;

import campaign.model.constants.CampaignConstants;
import campaign.model.database.Loader;
import campaign.model.thing.Thing;

public class BossLoader extends Loader<Thing> {

    public BossLoader() {
        super(AdventureConstants.BOSS_FILE);
    }

}
