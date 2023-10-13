package adventure.view.popup;

import adventure.model.AdvMainDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;

public class CardChooserDialog extends ChooserDialog<Card> {

    public void initialize(AdvMainDatabase mainDatabase, TargetList<Card> cards)
    {
        super.initialize(mainDatabase, cards, TargetType.CARD);
    }

}
