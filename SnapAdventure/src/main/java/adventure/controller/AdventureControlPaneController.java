package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureConstants;
import adventure.model.AdventureDatabase;
import adventure.model.AdvProfile;
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
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import snapMain.model.logger.MFormatter;
import snapMain.model.logger.MLogger;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
import snapMain.view.button.ButtonToolBar;
import snapMain.view.pane.FullViewPane;

import java.util.Collections;
import java.util.Optional;
import java.util.logging.FileHandler;

public class AdventureControlPaneController extends FullViewPaneController {

    @FXML
    DiceNode diceNode;
    @FXML
    Button backButton;
    @FXML
    Button exitButton;
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

    FileHandler logHandler;

    MLogger logger = new MLogger(AdventureControlPaneController.class);

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
        setLogHandler(a.getProfile());
    }

    private void setLogHandler(AdvProfile profile) {
        try {
            FileHandler logHandler = new FileHandler(profile.getLogFile(), true);
            MLogger.LOGGER.addHandler(logHandler);
            logHandler.setFormatter(new MFormatter());
        }
        catch(Exception e)
        {
            logger.error(e.getMessage(), e);
        }
    }

    private void failAdventureCheck() {
        if(adventure.failStateCheck()) {
            AdventureFailPane adventureFailPane = new AdventureFailPane();
            adventureFailPane.initialize(mainDatabase, adventure);
            changeScene(adventureFailPane);
            MLogger.LOGGER.info("Adventure Failed. Game Over.");
            MLogger.LOGGER.removeHandler(logHandler);
        }
    }

    @Override
    public Scene getCurrentScene() {
        return backButton.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
    }

    public Adventure getAdventure() {
        return adventure;
    }

    public void skipSection(Section section) {
        if(!adventure.hasAvailableSkips())
        {
            CardChooserDialog chooserDialog = new CardChooserDialog();
            chooserDialog.initialize(mainDatabase, adventure.getTeamCards(), TargetType.CARD,
                    "Choose a decoy to give you time to escape", getCurrentScene().getWindow());
            Optional<ActiveCard> capturedCard = chooserDialog.showAndWait();
            if(capturedCard.isPresent())
            {
                ActiveCard card = capturedCard.get();
                adventure.captureCard(card);
                refocusWindow();
            }
            else {
                refocusWindow();
                return;
            }
        }
        adventure.skipCurrentSection();
        worldDisplayNode.revealNextSection(section.getSectionNum());
        refreshToMatch();

    }

    public void refreshToMatch() {
        teamDisplayNode.refresh();
        worldDisplayNode.refresh(adventure.getCurrentWorld());
        adventure.saveAdventure();
        diceNode.refresh();
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
        optionsDialog.initialize(adventure.getFreeAgents(), false, getCurrentScene().getWindow());
        Optional<TargetList<ActiveCard>> filteredSelectables = optionsDialog.showAndWait();
        if(filteredSelectables.isPresent() && !filteredSelectables.get().isEmpty())
        {
            DraftDialog draftCardDialog = new DraftDialog();
            draftCardDialog.initialize(mainDatabase, adventure.draftCards(filteredSelectables.get()),
                    adventure.getTeamCards(),
                    getCurrentScene().getWindow());
            Optional<ActiveCard> card = draftCardDialog.showAndWait();
            card.ifPresent(value ->
            {
                if(draftCardDialog.isTeam()) {
                    adventure.addFreeAgentToTeam(value);
                }
                else {
                    adventure.addFreeAgentToTemp(value);
                }
            });
            refreshToMatch();
        }
        refocusWindow();
    }

    //TODO: Output a message if there are no valid cards to generate
    public void generateCards() {
        SelectionOptionsDialog optionsDialog = new SelectionOptionsDialog();
        optionsDialog.initialize(adventure.getFreeAgents(), true, getCurrentScene().getWindow());
        Optional<TargetList<ActiveCard>> filteredSelectables = optionsDialog.showAndWait();
        if(filteredSelectables.isPresent() && !optionsDialog.isMutiple())
        {
            RandomCardDisplayDialog randomDialog = new RandomCardDisplayDialog();
            randomDialog.initialize(mainDatabase, filteredSelectables.get().getRandom(), "Generated Cards:");
            Optional<ActiveCard> card = randomDialog.showAndWait();
            card.ifPresent(value ->
            {
                if(randomDialog.isTeam()) {
                    adventure.addFreeAgentToTeam(value);
                }
                else {
                    adventure.addFreeAgentToTemp(value);
                }
                refreshToMatch();
            });
        }
        else if(filteredSelectables.isPresent())
        {
            CardGeneratorDialog chooseDialog = new CardGeneratorDialog();
            ActiveCardList freeAgents = new ActiveCardList(adventure.getFreeAgents());
            Collections.shuffle(freeAgents.getThings());
            ActiveCardList randomCards = new ActiveCardList(freeAgents.getRandom(optionsDialog.getNumber()));
            chooseDialog.initialize(mainDatabase, randomCards, getCurrentScene().getWindow());
            Optional<ActiveCardList> chosenCards = chooseDialog.showAndWait();
            if(chosenCards.isPresent() && !chosenCards.get().isEmpty())
            {
                if(chooseDialog.isTeam()) {
                    adventure.addFreeAgentsToTeam(chosenCards.get());
                }
                else {
                    adventure.addFreeAgentsToTemp(chosenCards.get());
                }
                refreshToMatch();
            }
        }
        refocusWindow();
    }

    public void searchFreeAgent() {
        CardGainSearchSelectDialog cardSearchSelectDialog = new CardGainSearchSelectDialog();
        cardSearchSelectDialog.initialize(mainDatabase, adventure.getFreeAgents(), "Choose Free Agent",
                getCurrentScene().getWindow());
        Optional<ActiveCard> selection = cardSearchSelectDialog.showAndWait();
        if(selection.isPresent())
        {
            ActiveCard card = selection.get();
            if(cardSearchSelectDialog.isTeam()) {
                adventure.addFreeAgentToTeam(card);
            }
            else {
                {
                    adventure.addFreeAgentToTemp(card);
                }
            }
            refreshToMatch();
        }
        refocusWindow();
    }

    @FXML
    public void goBack()
    {
        adventure.saveAdventure();
        MLogger.LOGGER.removeHandler(logHandler);
        AdvMainMenuPane mainMenuPane = new AdvMainMenuPane();
        mainMenuPane.initialize(mainDatabase);
        changeScene(mainMenuPane);
    }

    @FXML
    public void exit()
    {
        adventure.saveAdventure();
        logger.info("Client closed");
        MLogger.LOGGER.removeHandler(logHandler);
        Platform.exit();
    }

    public AdventureDatabase getAdventureDatabase() {
        return adventureDatabase;
    }
}
