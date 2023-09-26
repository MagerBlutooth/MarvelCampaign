package adventure.model;

import campaign.model.database.Loader;
import campaign.model.thing.Thing;

public class SectionLoader extends Loader<Thing> {

    public SectionLoader() {
        super(AdventureConstants.SECTION_FILE);
    }

}
