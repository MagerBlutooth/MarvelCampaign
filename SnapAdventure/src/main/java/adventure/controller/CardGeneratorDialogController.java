package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import adventure.view.popup.CardGeneratorDialog;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.BaseGridActionController;
import snapMain.model.target.*;
import snapMain.view.ViewSize;
import snapMain.view.menu.FilterMenuButton;
import snapMain.view.menu.SortMenuButton;
import snapMain.view.node.GridDisplayNode;

public class CardGeneratorDialogController {

    @FXML
    ToggleButton toTempButton;
    @FXML
    ToggleButton toTeamButton;
    @FXML
    ToggleButton none;
    @FXML
    SortMenuButton<ActiveCard> sortButton;
    @FXML
    FilterMenuButton<ActiveCard> filterButton;
    @FXML
    GridDisplayNode<ActiveCard> choiceNodes;
    MainDatabase mainDatabase;
    ActiveCardList choices;
    ToggleGroup toggleGroup;


    public void initialize(AdvMainDatabase md, CardGeneratorDialog dialog, ActiveCardList selectables)
    {
        choices = selectables;
        BaseGridActionController<ActiveCard> cardChoiceActionController = new BaseGridActionController();
        cardChoiceActionController.initialize(md);
        mainDatabase = md;
        CardGeneratorDialogGridActionController gridActionController =
                new CardGeneratorDialogGridActionController();
        gridActionController.initialize(mainDatabase, dialog);
        choiceNodes.initialize(selectables, TargetType.CARD, gridActionController, ViewSize.SMALL, false);
        if(sortButton != null && filterButton != null) {
            sortButton.initialize(choiceNodes.getController());
            filterButton.initialize(choiceNodes.getController());
        }
        toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(toTeamButton, toTempButton);
        toggleGroup.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
        toTempButton.setSelected(true);
    }


    public ActiveCardList getChosen() {
        return choices;
    }


    public boolean isTeam() {
        return toTeamButton.isSelected();
    }

}
