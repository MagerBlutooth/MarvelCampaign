package adventure.model.thing;

import adventure.model.AdventureConstants;
import snapMain.model.database.Loader;
import snapMain.model.thing.BaseObject;

public class AdvLocationLoader extends Loader<BaseObject> {

    public AdvLocationLoader() {
        super(AdventureConstants.SECTION_FILE);
    }

}
