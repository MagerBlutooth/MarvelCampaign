package adventure.controller;

import adventure.view.popup.AdventureCardSearchSelectDialog;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import snapMain.controller.MainDatabase;
import snapMain.model.target.*;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

import java.util.ArrayList;

public class AdvCardSearchSelectDialogController {

    @FXML
    ButtonType okButton;
    @FXML
    ButtonType cancelButton;
    @FXML
    StackPane displayPane;
    @FXML
    TextField searchBar;
    @FXML
    GridDisplayNode<Card> choiceNodes;
    @FXML
    ToggleButton toTeamButton;
    @FXML
    ToggleButton toTempButton;
    ToggleGroup toggleGroup;
    Card choice;
    CardList choices;
    MainDatabase mainDatabase;
    AdventureCardSearchSelectDialog adventureCardSearchSelectDialog;

    public void initialize(MainDatabase md, AdventureCardSearchSelectDialog searchDialog, TargetList<Card> selectableCards)
    {
        mainDatabase = md;
        adventureCardSearchSelectDialog = searchDialog;
        toggleGroup = new ToggleGroup();
        choices = new CardList(selectableCards.getThings());
        toggleGroup.getToggles().addAll(toTeamButton, toTempButton);
        toTeamButton.setSelected(true);
        initializeNodes("");
        searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
            initializeNodes(newValue);
        });
    }

    public void initializeNodes(String text)
    {
        CardList filteredChoices = new CardList(new ArrayList<>());
        if(text.isEmpty())
        {
            filteredChoices.addAll(choices.getCards());
        }
        else {
            for (Card c : choices.getCards()) {
                String name = c.getName().toLowerCase();
                String searchString = searchBar.textProperty().get().toLowerCase();
                if (name.contains(searchString))
                    filteredChoices.add(c);
            }
        }
        ChooserDialogGridActionController<Card> gridController = new ChooserDialogGridActionController<>();
        gridController.initialize(mainDatabase, adventureCardSearchSelectDialog);
        choiceNodes.initialize(filteredChoices, TargetType.CARD, gridController, ViewSize.TINY, true);
    }

    public boolean isTeam() {
        return toTeamButton.isSelected();
    }

    public Card getChoice() {
        return choice;
    }

    public void setChoice(Card c) {
        displayPane.getChildren().clear();
        ControlNode<SnapTarget> viewNode = new ControlNode<>();
        viewNode.setMaxWidth(300.0);
        viewNode.initialize(mainDatabase, c, mainDatabase.grabImage(c), ViewSize.LARGE, true);
        displayPane.getChildren().add(viewNode);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.getChildren().add(new ControlNode<>());
        choice = c;
    }
}
