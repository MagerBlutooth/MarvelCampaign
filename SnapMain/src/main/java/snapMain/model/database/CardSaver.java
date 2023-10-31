package snapMain.model.database;

import snapMain.model.constants.SnapMainConstants;
import snapMain.model.target.Card;

public class CardSaver extends Saver<Card>{

    public CardSaver(TargetDatabase<Card> database)
    {
        super(SnapMainConstants.CARD_FILE, database);
    }
}
