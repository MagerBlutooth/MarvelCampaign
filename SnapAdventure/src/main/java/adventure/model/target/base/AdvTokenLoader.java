package adventure.model.target.base;

import adventure.model.AdventureConstants;
import snapMain.model.database.Loader;
import snapMain.model.target.BaseObject;

public class AdvTokenLoader extends Loader<BaseObject> {

    public AdvTokenLoader() {
        super(AdventureConstants.ADV_TOKEN_FILE);
    }

}
