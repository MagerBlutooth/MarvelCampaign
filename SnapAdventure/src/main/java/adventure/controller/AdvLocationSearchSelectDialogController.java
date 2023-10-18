package adventure.controller;

import adventure.model.target.AdvLocation;
import adventure.model.target.AdvLocationList;
import adventure.view.popup.Choosable;
import javafx.fxml.FXML;
import snapMain.controller.MainDatabase;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;

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
