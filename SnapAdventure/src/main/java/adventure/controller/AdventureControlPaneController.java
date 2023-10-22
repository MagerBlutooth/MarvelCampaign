package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureConstants;
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
import snapMain.model.logger.MFormatter;
import snapMain.model.logger.MLogger;
import snapMain.model.target.TargetList;
import snapMain.view.button.ButtonToolBar;
import snapMain.view.pane.FullViewPane;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.logging.FileHandler;

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

    FileHandler logHandler;

    final static MLogger adventureLogger = new MLogger(AdventureControlPaneController.class);


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
        setLogHandler(a.getProfileFile());
    }

    private void setLogHandler(String profileFile) {
        try {
            FileHandler logHandler = null;
            if (profileFile.contains("1")) {
                MLogger.LOGGER.info("Loading Profile 1...");
                logHandler = new FileHandler(AdventureConstants.LOG_1, true);
                MLogger.LOGGER.addHandler(logHandler);
            }
            else if (profileFile.contains("2")) {
                MLogger.LOGGER.info("Loading Profile 2...");
                logHandler = new FileHandler(AdventureConstants.LOG_2, true);
                MLogger.LOGGER.addHandler(logHandler);
            }
            else if (profileFile.contains("3")) {
                MLogger.LOGGER.info("Loading Profile 3...");
                logHandler = new FileHandler(AdventureConstants.LOG_3, true);
                MLogger.LOGGER.addHandler(logHandler);
            }
            assert logHandler != null;
            logHandler.setFormatter(new MFormatter());
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
        return buttonToolBar.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
        AdvMainMenuPane mainMenuPane = new AdvMainMenuPane();
        mainMenuPane.initialize(mainDatabase);
        buttonToolBar.initialize(mainMenuPane, logHandler);
    }

    public Adventure getAdventure() {
        return adventure;
    }

    public void skipSection(Section section) {
        logInfo("Section " + adventure.getCurrentWorldNum() + "-" + adventure.getCurrentSectionNum()
                + " skipped");
        adventure.skipCurrentSection();
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
        logInfo("Section " + adventure.getCurrentWorldNum() + "-" + adventure.getCurrentSectionNum()
                + " completed");
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
                if(draftCardDialog.isTeam()) {
                    adventure.addFreeAgentToTeam(value);
                    MLogger.LOGGER.info("Drafted " + value + " to team.");
                }
                else {
                    adventure.addFreeAgentToTemp(value);
                    MLogger.LOGGER.info("Drafted " + value + " to temp.");
                }
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
                if(randomDialog.isTeam()) {
                    adventure.addFreeAgentToTeam(value);
                    MLogger.LOGGER.info("Added " + value + " to team.");
                }
                else {
                    adventure.addFreeAgentToTemp(value);
                    MLogger.LOGGER.info("Added " + value + " to temp.");
                }

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
                if(chooseDialog.isTeam()) {
                    adventure.addFreeAgentsToTeam(chosenCards.get());
                    MLogger.LOGGER.info("Added " + chosenCards.get() + " to team.");
                }
                else {
                    adventure.addFreeAgentsToTemp(chosenCards.get());
                    MLogger.LOGGER.info("Added " + chosenCards.get() + " to team.");
                }
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
            if(cardSearchSelectDialog.isTeam()) {
                adventure.addFreeAgentToTeam(card);
                MLogger.LOGGER.info("Added " + selection.get() + " to team.");
            }
            else {
                {
                    adventure.addFreeAgentToTemp(card);
                    MLogger.LOGGER.info("Added " + selection.get() + " to temp.");
                }
            }
            refreshToMatch();
        }

    }

    public AdventureDatabase getAdventureDatabase() {
        return adventureDatabase;
    }

    public void logInfo(String string)
    {
        adventureLogger.info(string);
    }
}
