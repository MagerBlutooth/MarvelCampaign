package adventure.controller;

import adventure.model.thing.AdvLocation;
import adventure.model.thing.AdvLocationList;
import adventure.view.popup.Choosable;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import snapMain.controller.MainDatabase;
import snapMain.model.target.CardList;
import snapMain.model.target.SnapTarget;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

import java.util.ArrayList;

public class AdvLocationSearchSelectDialogController
        extends AdvSearchSelectDialogController<AdvLocation> {

    @Override
    public void initialize(MainDatabase md, Choosable<AdvLocation> searchDialog, TargetList<AdvLocation> selectableCards)
    {
        mainDatabase = md;
        searchSelectDialog = searchDialog;
        choices = selectableCards;
        initializeNodes("");
        searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
            initializeNodes(newValue);
        });
    }

    public void initializeNodes(String text)
    {
        AdvLocationList filteredChoices = new AdvLocationList(new ArrayList<>());
        if(text.isEmpty())
        {
            filteredChoices.addAll(choices.getThings());
        }
        else {
            for (AdvLocation t : choices.getThings()) {
                String name = t.getName().toLowerCase();
                String searchString = searchBar.textProperty().get().toLowerCase();
                if (name.contains(searchString))
                    filteredChoices.add(t);
            }
        }
        ChooserDialogGridActionController<AdvLocation> gridController = new ChooserDialogGridActionController<>();
        gridController.initialize(mainDatabase, searchSelectDialog);
        choiceNodes.initialize(filteredChoices, TargetType.LOCATION, gridController, ViewSize.SMALL, true);
    }

    @FXML
    public void selectRandom()
    {
        AdvLocationList locs = new AdvLocationList(new ArrayList<>());
        locs = locs.cloneNewList(choices.getThings());
        locs.shuffle();
        setChoice(locs.get(0));
    }

}
