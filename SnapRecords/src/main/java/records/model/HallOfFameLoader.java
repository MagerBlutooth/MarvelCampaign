package records.model;

import campaign.model.database.Loader;
import campaign.model.thing.Card;

public class HallOfFameLoader extends Loader<Card> {

    public HallOfFameLoader() {
        super(RecordConstants.HOF_FILE);
    }

}
