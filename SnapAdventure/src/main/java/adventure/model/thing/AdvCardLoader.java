package adventure.model.thing;

import adventure.model.AdventureConstants;
import snapMain.model.database.Loader;
import snapMain.model.target.BaseObject;

public class AdvCardLoader extends Loader<BaseObject> {

    public AdvCardLoader() {
        super(AdventureConstants.BOSS_FILE);
    }

}
