package adventure.controller;

import adventure.view.popup.Choosable;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import snapMain.controller.MainDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;

import java.util.ArrayList;

public class CardGainSearchSelectDialogController extends AdvSearchSelectDialogController<Card> {

    @FXML
    ToggleButton toTeamButton;
    @FXML
    ToggleButton toTempButton;
    ToggleGroup toggleGroup;

    @Override
    public void initialize(MainDatabase md, Choosable<Card> searchDialog, TargetList<Card> selectableCards)
    {
        mainDatabase = md;
        searchSelectDialog = searchDialog;
        toggleGroup = new ToggleGroup();
        choices = selectableCards;
        toggleGroup.getToggles().addAll(toTeamButton, toTempButton);
        toTeamButton.setSelected(true);
        initializeNodes("");
        searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
            initializeNodes(newValue);
        });
    }

    public void initializeNodes(String text)
    {
        TargetList<Card> filteredChoices = new CardList(new ArrayList<>());
        if(text.isEmpty())
        {
            filteredChoices.addAll(choices.getThings());
        }
        else {
            for (Card t : choices.getThings()) {
                String name = t.getName().toLowerCase();
                String searchString = searchBar.textProperty().get().toLowerCase();
                if (name.contains(searchString))
                    filteredChoices.add(t);
            }
        }
        ChooserDialogGridActionController<Card> gridController = new ChooserDialogGridActionController<>();
        gridController.initialize(mainDatabase, searchSelectDialog);
        choiceNodes.initialize(filteredChoices, TargetType.CARD, gridController, ViewSize.TINY, true);
    }

    public boolean isTeam() {
        return toTeamButton.isSelected();
    }

}
