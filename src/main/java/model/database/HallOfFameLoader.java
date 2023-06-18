package model.database;

import model.constants.RecordConstants;
import model.thing.Card;
import model.thing.HallOfFameEntry;
import model.thing.Thing;

public class HallOfFameLoader extends Loader<Card>{

    public HallOfFameLoader() {
        super(RecordConstants.HOF_FILE);
    }

}
