package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.target.Section;
import adventure.view.node.AdventureActionNode;
import adventure.view.node.DiceNode;
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
import snapMain.view.button.ButtonToolBar;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AdventureControlPaneController extends AdvPaneController {

    @FXML
    DiceNode diceNode;
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
        adventure = a;
        adventureDatabase = adventure.getAdventureDatabase();
        teamDisplayNode.initialize(database, a.getTeam(), adventureControlPane);
        worldDisplayNode.initialize(database,a.getCurrentWorld(), adventureControlPane);
        adventureActionNode.initialize(database, adventure, adventureControlPane);
        diceNode.initialize(database);
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

    //TODO: Output a message if there are no valid cards to draft. Add fewer if not enough.
    @FXML
    public void draftCard() {
        SelectionOptionsDialog optionsDialog = new SelectionOptionsDialog();
        optionsDialog.initialize(adventure.getFreeAgents(), false);
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
                refreshToMatch();
            });
        }

    }

    //TODO: Output a message if there are no valid cards to generate
    public void generateCards() {
        SelectionOptionsDialog optionsDialog = new SelectionOptionsDialog();
        optionsDialog.initialize(adventure.getFreeAgents(), true);
        Optional<TargetList<Card>> filteredSelectables = optionsDialog.showAndWait();
        if(filteredSelectables.isPresent() && !optionsDialog.isMutiple())
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
        else if(filteredSelectables.isPresent())
        {
            CardGeneratorDialog chooseDialog = new CardGeneratorDialog();
            CardList freeAgents = new CardList(adventure.getFreeAgents());
            Collections.shuffle(freeAgents.getCards());
            CardList randomCards = new CardList(freeAgents.getRandom(optionsDialog.getNumber()));
            chooseDialog.initialize(mainDatabase, randomCards);
            Optional<CardList> chosenCards = chooseDialog.showAndWait();
            if(chosenCards.isPresent() && !chosenCards.get().isEmpty())
            {
                if(chooseDialog.isTeam())
                    adventure.addFreeAgentsToTeam(chosenCards.get());
                else
                    adventure.addFreeAgentsToTemp(chosenCards.get());
                refreshToMatch();
            }
        }

    }

    public void searchFreeAgent() {
        CardGainSearchSelectDialog cardSearchSelectDialog = new CardGainSearchSelectDialog();
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
            refreshToMatch();
        }

    }

    public AdventureDatabase getAdventureDatabase() {
        return adventureDatabase;
    }
}
