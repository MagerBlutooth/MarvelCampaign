package adventure.model;

import campaign.model.database.Saver;
import campaign.model.database.ThingDatabase;

public class SectionSaver extends Saver<Section> {

    public SectionSaver(ThingDatabase<Section> database)
    {
        super(AdventureConstants.SECTION_FILE, database);
    }
}
