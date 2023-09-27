package adventure.model;

import campaign.model.constants.CampaignConstants;
import campaign.model.database.Saver;
import campaign.model.database.ThingDatabase;
import campaign.model.thing.Location;

public class SectionSaver extends Saver<Section> {

    public SectionSaver(ThingDatabase<Section> database)
    {
        super(AdventureConstants.SECTION_FILE, database);
    }
}
