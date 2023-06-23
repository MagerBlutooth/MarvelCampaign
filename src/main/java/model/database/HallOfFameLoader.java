package model.database;

import model.constants.RecordConstants;
import model.thing.Card;

public class HallOfFameLoader extends Loader<Card>{

    public HallOfFameLoader() {
        super(RecordConstants.HOF_FILE);
    }

}
