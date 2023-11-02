package adventure.controller.dialog;

import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import adventure.view.popup.Choosable;
import javafx.fxml.FXML;
import snapMain.controller.MainDatabase;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;

import java.util.ArrayList;

public class CardSearchSelectDialogController extends AdvSearchSelectDialogController<ActiveCard> {

    @Override
    public void initialize(MainDatabase md, Choosable<ActiveCard> searchDialog, TargetList<ActiveCard> selectableCards,
            String header)
    {
        super.initialize(md, searchDialog, selectableCards, header);
    }

    public void initializeNodes(String text)
    {
        TargetList<ActiveCard> filteredChoices = new ActiveCardList(new ArrayList<>());
        if(text.isEmpty())
        {
            filteredChoices.addAll(choices.getThings());
        }
        else {
            for (ActiveCard t : choices.getThings()) {
                String name = t.getName().toLowerCase();
                String searchString = searchBar.textProperty().get().toLowerCase();
                if (name.contains(searchString))
                    filteredChoices.add(t);
            }
        }
        ChooserDialogGridActionController<ActiveCard> gridController = new ChooserDialogGridActionController<>();
        gridController.initialize(mainDatabase, searchSelectDialog);
        choiceNodes.initialize(filteredChoices, TargetType.CARD, gridController, ViewSize.TINY, true);
    }

    @FXML
    public void selectRandom()
    {
        ActiveCardList cards = new ActiveCardList(new ArrayList<>());
        cards = cards.cloneNewList(choices.getThings());
        cards.shuffle();
        searchSelectDialog.enableOKButton();
        setChoice(cards.get(0));
    }

}
