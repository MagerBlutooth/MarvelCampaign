package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.stats.MatchResult;
import adventure.model.thing.Section;
import adventure.view.node.AdventureActionNode;
import adventure.view.node.TeamDisplayNode;
import adventure.view.node.WorldDisplayNode;
import adventure.view.pane.AdvMainMenuPane;
import adventure.view.pane.AdventureControlPane;
import adventure.view.popup.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
import snapMain.view.button.ButtonToolBar;

import java.util.Optional;

public class AdventureControlPaneController extends AdvPaneController {

    @FXML
    ButtonToolBar buttonToolBar;
    @FXML
    TeamDisplayNode teamDisplayNode;
    @FXML
    WorldDisplayNode worldDisplayNode;
    @FXML
    AdventureActionNode adventureActionNode;
    @FXML
    AdventureControlPane adventureControlPane;
    Adventure adventure;
    AdventureDatabase adventureDatabase;


    public void initialize(AdvMainDatabase database, Adventure a)
    {
        super.initialize(database);
        initializeButtonToolBar();
        mainDatabase = database;
        adventureDatabase = new AdventureDatabase(database);
        adventure = a;
        teamDisplayNode.initialize(database, a.getTeam(), a);
        worldDisplayNode.initialize(database,a.getCurrentWorld(), adventureControlPane);
        adventureActionNode.initialize(database, adventure, adventureControlPane);
        adventure.saveAdventure();
    }

    @Override
    public Scene getCurrentScene() {
        return buttonToolBar.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
        AdvMainMenuPane mainMenuPane = new AdvMainMenuPane();
        mainMenuPane.initialize(mainDatabase);
        buttonToolBar.initialize(mainMenuPane);
    }

    public Adventure getAdventure() {
        return adventure;
    }

    public void skipSection(Section section) {
        worldDisplayNode.revealNextSection(section.getSectionNum());
        refreshToMatch();
    }

    public void refreshToMatch() {
        teamDisplayNode.refresh();
        worldDisplayNode.refresh(adventure.getCurrentWorld());
        adventure.saveAdventure();
    }

    public void completeSection() {
        adventure.completeCurrentSection();
        worldDisplayNode.revealBossCheck();
        refreshToMatch();
    }

    public void completeWorld() {
        adventure.completeCurrentWorld();
        refreshToMatch();
    }

    //TODO: Output a message if there are no valid cards to draft. Add fewer if not enough.
    @FXML
    public void draftCard() {
        SelectionOptionsDialog optionsDialog = new SelectionOptionsDialog();
        optionsDialog.initialize(adventure.getFreeAgents());
        Optional<TargetList<Card>> filteredSelectables = optionsDialog.showAndWait();
        if(filteredSelectables.isPresent() && !filteredSelectables.get().isEmpty())
        {
            DraftDialog draftCardDialog = new DraftDialog();
            draftCardDialog.initialize(mainDatabase, adventure.draftCards(filteredSelectables.get()));
            Optional<Card> card = draftCardDialog.showAndWait();
            card.ifPresent(value ->
            {
                if(draftCardDialog.isTeam())
                    adventure.addFreeAgentToTeam(value);
                else
                    adventure.addFreeAgentToTemp(value);

            });
        }
        refreshToMatch();
    }

    public void healCard() {
        CardChooserDialog chooserDialog = new CardChooserDialog();
        chooserDialog.initialize(mainDatabase, adventure.getWoundedCards(), TargetType.CARD);
        Optional<Card> card = chooserDialog.showAndWait();
        card.ifPresent(value -> adventure.healCard(value));
        refreshToMatch();
    }

    //TODO: Output a message if there are no valid cards to generate
    public void generateCard() {
        SelectionOptionsDialog optionsDialog = new SelectionOptionsDialog();
        optionsDialog.initialize(adventure.getFreeAgents());
        Optional<TargetList<Card>> filteredSelectables = optionsDialog.showAndWait();
        if(filteredSelectables.isPresent() && !filteredSelectables.get().isEmpty())
        {
            RandomDisplayDialog randomDialog = new RandomDisplayDialog();
            randomDialog.initialize(mainDatabase, filteredSelectables.get().getRandom());
            Optional<Card> card = randomDialog.showAndWait();
            card.ifPresent(value ->
            {
                if(randomDialog.isTeam())
                    adventure.addFreeAgentToTeam(value);
                else
                    adventure.addFreeAgentToTemp(value);

            });
        }
        refreshToMatch();
    }

    public void searchFreeAgent() {
        CardSearchSelectDialog cardSearchSelectDialog = new CardSearchSelectDialog();
        cardSearchSelectDialog.initialize(mainDatabase, adventure.getFreeAgents());
        Optional<Card> selection = cardSearchSelectDialog.showAndWait();
        if(selection.isPresent())
        {
            Card card = selection.get();
            if(cardSearchSelectDialog.isTeam())
                adventure.addFreeAgentToTeam(card);
            else {
                adventure.addFreeAgentToTemp(card);
            }
        }
        refreshToMatch();
    }

    public void createClone() {
        CardSearchSelectDialog cardSearchSelectDialog = new CardSearchSelectDialog();
        cardSearchSelectDialog.initialize(mainDatabase, adventureDatabase.getCardList());
        Optional<Card> selection = cardSearchSelectDialog.showAndWait();
        if(selection.isPresent())
        {
            Card card = selection.get();
            if(cardSearchSelectDialog.isTeam())
                adventure.addFreeAgentToTeam(card);
            else {
                adventure.addFreeAgentToTemp(card);
            }
        }
        refreshToMatch();
    }

    public void updateStats(CardList deck, MatchResult result) {
        adventure.updateStats(deck, result);
    }

    public AdventureDatabase getAdventureDatabase() {
        return adventureDatabase;
    }
}
