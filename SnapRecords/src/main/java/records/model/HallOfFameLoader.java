package records.model;

import snapMain.model.database.Loader;
import snapMain.model.thing.Card;

public class HallOfFameLoader extends Loader<Card> {

    public HallOfFameLoader() {
        super(RecordConstants.HOF_FILE);
    }

}
