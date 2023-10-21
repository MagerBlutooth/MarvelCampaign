package adventure.controller;

import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import adventure.view.popup.Choosable;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.BaseGridActionController;
import snapMain.model.target.Card;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;

import java.util.ArrayList;

public class CardExhaustionConfirmationDialogController {

    @FXML
    GridDisplayNode<ActiveCard> exhaustedCardList;
    @FXML
    GridDisplayNode<ActiveCard> recoveredCardList;

    public void initialize(MainDatabase mainDatabase, ActiveCardList exhausted, ActiveCardList recovered)
    {
        BaseGridActionController<ActiveCard> simpleController = new BaseGridActionController<>();
        simpleController.initialize(mainDatabase);
        exhaustedCardList.initialize(exhausted, TargetType.CARD, simpleController, ViewSize.MEDIUM, true);
        recoveredCardList.initialize(recovered, TargetType.CARD, simpleController, ViewSize.MEDIUM, true);
    }

}
