package adventure.view.popup;

import adventure.model.AdvMainDatabase;
import adventure.model.thing.AdvCard;
import snapMain.model.target.Card;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;

public class AdvCardChooserDialog extends ChooserDialog<AdvCard> {

    public void initialize(AdvMainDatabase mainDatabase, TargetList<AdvCard> cards)
    {
        super.initialize(mainDatabase, cards, TargetType.ADV_CARD);
    }

}
