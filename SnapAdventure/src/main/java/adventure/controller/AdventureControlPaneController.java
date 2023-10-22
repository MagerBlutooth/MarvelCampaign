package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import adventure.model.target.Section;
import adventure.view.node.AdventureActionNode;
import adventure.view.node.DiceNode;
import adventure.view.node.TeamDisplayNode;
import adventure.view.node.WorldDisplayNode;
import adventure.view.pane.AdvMainMenuPane;
import adventure.view.pane.AdventureControlPane;
import adventure.view.pane.AdventureFailPane;
import adventure.view.popup.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import snapMain.model.target.TargetList;
import snapMain.view.button.ButtonToolBar;

import java.util.Collections;
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

    private void failAdventureCheck() {
        if(adventure.failStateCheck()) {
            AdventureFailPane adventureFailPane = new AdventureFailPane();
            adventureFailPane.initialize(mainDatabase, adventure);
            changeScene(adventureFailPane);
        }
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
        failAdventureCheck();
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
        Optional<TargetList<ActiveCard>> filteredSelectables = optionsDialog.showAndWait();
        if(filteredSelectables.isPresent() && !filteredSelectables.get().isEmpty())
        {
            DraftDialog draftCardDialog = new DraftDialog();
            draftCardDialog.initialize(mainDatabase, adventure.draftCards(filteredSelectables.get()));
            Optional<ActiveCard> card = draftCardDialog.showAndWait();
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
        Optional<TargetList<ActiveCard>> filteredSelectables = optionsDialog.showAndWait();
        if(filteredSelectables.isPresent() && !optionsDialog.isMutiple())
        {
            RandomCardDisplayDialog randomDialog = new RandomCardDisplayDialog();
            randomDialog.initialize(mainDatabase, filteredSelectables.get().getRandom());
            Optional<ActiveCard> card = randomDialog.showAndWait();
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
            ActiveCardList freeAgents = new ActiveCardList(adventure.getFreeAgents());
            Collections.shuffle(freeAgents.getThings());
            ActiveCardList randomCards = new ActiveCardList(freeAgents.getRandom(optionsDialog.getNumber()));
            chooseDialog.initialize(mainDatabase, randomCards);
            Optional<ActiveCardList> chosenCards = chooseDialog.showAndWait();
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
        Optional<ActiveCard> selection = cardSearchSelectDialog.showAndWait();
        if(selection.isPresent())
        {
            ActiveCard card = selection.get();
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
