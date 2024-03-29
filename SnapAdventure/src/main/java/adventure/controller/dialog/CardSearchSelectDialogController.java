package adventure.controller.dialog;

import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import adventure.view.popup.CardDisplayPopup;
import adventure.view.popup.Choosable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.BaseGridActionController;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;

import java.util.ArrayList;

public class CardSearchSelectDialogController extends AdvSearchSelectDialogController<ActiveCard> {

    @FXML
    public Button viewTeamButton;

    ActiveCardList teamCards;

    public void initialize(MainDatabase md, Choosable<ActiveCard> searchDialog, TargetList<ActiveCard> selectableCards,
            ActiveCardList tCards, String header)
    {
        super.initialize(md, searchDialog, selectableCards, header);
        teamCards = tCards;
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

    public void viewTeam()
    {
        CardDisplayPopup cardDisplayPopup = new CardDisplayPopup(teamCards,
                viewTeamButton.localToScreen(-325,0.0));
        BaseGridActionController<ActiveCard> baseGridActionController = new BaseGridActionController<>();
        baseGridActionController.initialize(mainDatabase);
        cardDisplayPopup.initialize(baseGridActionController);
        cardDisplayPopup.setPrefCols(12);
        cardDisplayPopup.enableVBar();
        cardDisplayPopup.show();
        cardDisplayPopup.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (! isNowFocused) {
                cardDisplayPopup.hide();
            }
        });
    }



}
