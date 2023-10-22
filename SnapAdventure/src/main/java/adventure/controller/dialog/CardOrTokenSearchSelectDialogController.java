package adventure.controller.dialog;

import snapMain.model.target.Playable;
import adventure.model.target.PlayableList;
import adventure.view.popup.Choosable;
import javafx.scene.control.ToggleGroup;
import snapMain.controller.MainDatabase;
import snapMain.model.target.*;
import snapMain.view.ViewSize;

import java.util.ArrayList;

public class CardOrTokenSearchSelectDialogController extends AdvSearchSelectDialogController<Playable> {

    ToggleGroup toggleGroup;

    @Override
    public void initialize(MainDatabase md, Choosable<Playable> searchDialog, TargetList<Playable> selectableCards)
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
        TargetList<Playable> filteredChoices = new PlayableList(new ArrayList<>());
        if(text.isEmpty())
        {
            filteredChoices.addAll(choices.getThings());
        }
        else {
            for (Playable p : choices.getThings()) {
                String name = p.getName().toLowerCase();
                String searchString = searchBar.textProperty().get().toLowerCase();
                if (name.contains(searchString))
                    filteredChoices.add(p);
            }
        }
        ChooserDialogGridActionController<Playable> gridController = new ChooserDialogGridActionController<>();
        gridController.initialize(mainDatabase, searchSelectDialog);
        choiceNodes.initialize(filteredChoices, TargetType.CARD, gridController, ViewSize.TINY, true);
    }

}
