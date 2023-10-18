package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.view.popup.CardGeneratorDialog;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.BaseGridActionController;
import snapMain.model.target.*;
import snapMain.view.ViewSize;
import snapMain.view.menu.FilterMenuButton;
import snapMain.view.menu.SortMenuButton;
import snapMain.view.node.GridDisplayNode;

import java.util.ArrayList;

public class CardGeneratorDialogController {

    @FXML
    ToggleButton toTempButton;
    @FXML
    ToggleButton toTeamButton;
    @FXML
    ToggleButton none;
    @FXML
    SortMenuButton<Card> sortButton;
    @FXML
    FilterMenuButton<Card> filterButton;
    @FXML
    GridDisplayNode<Card> choiceNodes;
    MainDatabase mainDatabase;
    CardList choices;
    ToggleGroup toggleGroup;


    public void initialize(AdvMainDatabase md, CardGeneratorDialog dialog, CardList selectables)
    {
        choices = selectables;
        BaseGridActionController<Card> cardChoiceActionController = new BaseGridActionController();
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


    public CardList getChosen() {
        return choices;
    }


    public boolean isTeam() {
        return toTeamButton.isSelected();
    }

}
