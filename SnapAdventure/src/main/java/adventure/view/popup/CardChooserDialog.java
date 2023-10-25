package adventure.view.popup;

import adventure.model.AdvMainDatabase;
import adventure.model.target.ActiveCard;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;

public class CardChooserDialog extends ChooserDialog<ActiveCard> {

    public void initialize(AdvMainDatabase mainDatabase, TargetList<ActiveCard> cards, String header)
    {
        super.initialize(mainDatabase, cards, TargetType.CARD, header);
    }

}
