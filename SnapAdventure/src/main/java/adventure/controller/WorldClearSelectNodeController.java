package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.target.ActiveCard;
import adventure.view.pane.AdventureControlPane;
import adventure.view.popup.CardChooserDialog;
import adventure.view.popup.ConfirmationDialog;
import adventure.view.popup.SimpleChooserDialog;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import snapMain.controller.grid.BaseGridActionController;
import snapMain.model.target.Card;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.thing.CardView;

import java.util.Optional;

public class WorldClearSelectNodeController extends AdvPaneController  {

    @FXML
    Button bossButton;
    @FXML
    Button healButton;
    @FXML
    Button draftButton;
    @FXML
    CardView bossDisplay;
    @FXML
    CardView draftCardDisplay;
    @FXML
    CardView healCardDisplay;
    Adventure adventure;
    AdventureControlPane adventureControlPane;

    public void initialize(AdvMainDatabase database, Adventure a, AdventureControlPane aPane)
    {
        mainDatabase = database;
        adventure = a;
        adventureControlPane = aPane;
        BaseGridActionController<Card> gridActionController = new BaseGridActionController<>();
        gridActionController.initialize(database);
    }

    @FXML
    public void draftCard()
    {
        SimpleChooserDialog<ActiveCard> chooserDialog = new SimpleChooserDialog<>();
        chooserDialog.initialize(mainDatabase, adventure.draftCards(), TargetType.CARD);
        Optional<ActiveCard> card = chooserDialog.showAndWait();
        card.ifPresent(value -> draftCardDisplay.initialize(mainDatabase, card.get().getCard(), ViewSize.MEDIUM, false));
        draftButton.setDisable(true);
    }

    @FXML
    public void healCard()
    {
        SimpleChooserDialog<ActiveCard> chooserDialog = new SimpleChooserDialog<>();
        chooserDialog.initialize(mainDatabase, adventure.getWoundedCards(), TargetType.CARD);
        Optional<ActiveCard> card = chooserDialog.showAndWait();
        card.ifPresent(value -> healCardDisplay.initialize(mainDatabase, card.get().getCard(), ViewSize.MEDIUM, false));
        healButton.setDisable(true);

    }

    @FXML
    public void gainBoss()
    {
        bossDisplay.initialize(mainDatabase, adventure.getBossCard(), ViewSize.MEDIUM, false);
        bossButton.setDisable(true);
    }

    @FXML
    public void completeWorld()
    {
        if(!buttonsDisabled())
        {
            ConfirmationDialog dialog = new ConfirmationDialog("You have uncollected rewards. Proceed anyway?");
            Optional<ButtonType> result = dialog.showAndWait();
            if(result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.NO)
                return;
        }
        adventure.completeCurrentWorld();
        ActiveCard draftedCard = collectCard(draftCardDisplay);
        if(draftedCard != null && draftedCard.isActualThing())
            adventure.addCardToTeam(draftedCard);

        ActiveCard bossCard = collectCard(bossDisplay);
        if(bossCard != null && bossCard.isActualThing())
            adventure.addCardToTeam(bossCard);
        ActiveCard healCard = collectCard(healCardDisplay);
        if(healCard != null && healCard.isActualThing()) {
            adventure.healCard(healCard);
        }
        adventureControlPane.refreshToMatch();
        changeScene(adventureControlPane);
    }

    private ActiveCard collectCard(CardView cardDisplay) {
        Card collectedCard = cardDisplay.getCard();
        if(collectedCard != null)
            return adventure.lookupCard(collectedCard.getID());
        return null;
    }

    private boolean buttonsDisabled() {
        return bossButton.isDisable() && healButton.isDisable() && draftButton.isDisable();
    }

    @Override
    public Scene getCurrentScene() {
        return bossButton.getScene();
    }

    @Override
    public void initializeButtonToolBar() {

    }
}
