package adventure.model.target.base;

import adventure.model.AdventureConstants;
import snapMain.model.database.Loader;
import snapMain.model.target.BaseObject;

public class AdvLocationLoader extends Loader<BaseObject> {
    public AdvLocationLoader() {
        super(AdventureConstants.SECTION_FILE);
    }
}
