package adventure.view.popup;

import adventure.model.AdvMainDatabase;
import adventure.model.target.base.AdvCard;
import javafx.stage.Window;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;

public class AdvCardChooserDialog extends ChooserDialog<AdvCard> {

    public void initialize(AdvMainDatabase mainDatabase, TargetList<AdvCard> cards, String header, Window owner)
    {
        super.initialize(mainDatabase, cards, TargetType.ADV_CARD, header, owner);
    }

}
