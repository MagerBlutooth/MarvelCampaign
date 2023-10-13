package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureConstants;
import adventure.model.adventure.Adventure;
import adventure.model.thing.*;
import adventure.view.node.AdvLocationControlNode;
import adventure.view.node.EnemyControlNode;
import adventure.view.node.HPDisplayNode;
import adventure.view.pane.AdventureControlPane;
import adventure.view.pane.WorldClearPane;
import adventure.view.popup.AdvLocationSearchSelectDialog;
import adventure.view.popup.CardChooserDialog;
import adventure.view.pane.DeckConstructorPane;
import adventure.view.popup.HPDialog;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import snapMain.controller.grid.BaseGridActionController;
import snapMain.model.target.Card;
import snapMain.model.target.Playable;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.button.ButtonToolBar;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.pane.FullViewPane;

import java.util.Optional;

public class SectionViewPaneController extends AdvPaneController {

    @FXML
    FullViewPane sectionViewPane;
    @FXML
    Label enemyEffectText;
    @FXML
    EnemyControlNode enemyView;
    @FXML
    HPDisplayNode hpDisplay;
    @FXML
    StackPane stationedDisplayBox;
    @FXML
    StackPane pickupDisplayBox;
    @FXML
    ButtonToolBar buttonToolBar;
    @FXML
    AdvLocationControlNode sectionView;
    @FXML
    Label locationEffectText;

    @FXML
    Button skipButton;
    @FXML
    Button changeButton;
    @FXML
    Button completeButton;
    @FXML
    Button randomizeButton;
    @FXML
    Button stationButton;

    AdventureControlPane controlPane;
    Section section;
    Adventure adventure;
    GridDisplayNode<Card> stationedDisplay;
    Enemy enemy;

    public void initialize(AdvMainDatabase dB, AdventureControlPane cP, Section s) {
        mainDatabase = dB;
        controlPane = cP;
        section = s;
        enemy = s.getEnemy();
        adventure = cP.getAdventure();
        AdvLocation l = s.getLocation();
        sectionView.initialize(mainDatabase, s.getLocation(), mainDatabase.grabImage(s.getLocation()),
                ViewSize.LARGE, s.isRevealed());
        locationEffectText.setText(l.getEffect());
        enemyEffectText.setText(enemy.getEffect());
        enemyView.initialize(mainDatabase, enemy, mainDatabase.grabImage(enemy.getSubject()),
                ViewSize.MEDIUM, false);
        hpDisplay.initialize(enemy);
        completeButton.disableProperty().bind(Bindings.lessThan(0,hpDisplay.getHPProperty()));
        initializePickups(s);
        initializeStations(s);
        initializeButtonToolBar();
    }

    private void initializeStations(Section s) {
        stationedDisplay = new GridDisplayNode<>();
            BaseGridActionController<Card> gridActionController = new BaseGridActionController<>();
            gridActionController.initialize(mainDatabase);
             stationedDisplay.initialize(s.getStationedCards(), TargetType.CARD_OR_TOKEN, gridActionController,
                    ViewSize.TINY, false);
             stationedDisplay.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
             stationedDisplay.setMinHeight(200);
            stationedDisplayBox.getChildren().add(stationedDisplay);
    }

    private void initializePickups(Section s) {
            GridDisplayNode<Playable> pickupDisplay = new GridDisplayNode<>();
            BaseGridActionController<Playable> gridActionController = new BaseGridActionController<>();
            gridActionController.initialize(mainDatabase);
            pickupDisplay.initialize(s.getPickups(), TargetType.CARD_OR_TOKEN, gridActionController,
                    ViewSize.TINY, false);
            pickupDisplay.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            pickupDisplay.setMinHeight(200);
            pickupDisplayBox.getChildren().add(pickupDisplay);
    }

    @Override
    public Scene getCurrentScene() {
        return sectionView.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
        buttonToolBar.initialize(controlPane);
    }

    @FXML
    public void changeLocation()
    {
        AdvLocationSearchSelectDialog locationSearchSelectDialog = new AdvLocationSearchSelectDialog();
        locationSearchSelectDialog.initialize(mainDatabase, new AdvLocationList(adventure.getAvailableLocations()));
        Optional<AdvLocation> location = locationSearchSelectDialog.showAndWait();
        if(location.isPresent()) {
            AdvLocation newLoc = location.get();
            adventure.updateSection(newLoc, section.getSectionNum());
            sectionView.update(newLoc);
            locationEffectText.setText(newLoc.getEffect());
        }
        controlPane.refreshToMatch();
    }

    @FXML
    public void randomize()
    {
        adventure.randomizeSection(section);
        sectionView.update(section.getLocation());
        locationEffectText.setText(section.getEffect());
        controlPane.refreshToMatch();
    }

    @FXML
    public void completeSection()
    {
        section.complete();
        adventure.collectPickups(section);
        if(!(section instanceof BossSection)) {
            controlPane.completeCurrentSection();
            changeScene(controlPane);
        }
        else {
            defeatBoss();
        }
    }

    private void defeatBoss() {
        adventure.reclaimCards();
        WorldClearPane worldClearPane = new WorldClearPane();
        worldClearPane.initialize(mainDatabase, adventure, controlPane);
        changeScene(worldClearPane);
    }

    @FXML
    public void skipSection()
    {
        controlPane.skipSection(section);
        changeScene(controlPane);
    }

    @FXML
    public void changeHP()
    {
        HPDialog dialog = new HPDialog();
        dialog.initialize(enemy.getCurrentHP());
        Optional<Integer> newHP = dialog.showAndWait();
        newHP.ifPresent(integer -> enemy.setCurrentHP(integer));
        hpDisplay.update();
        adventure.saveAdventure();
    }

    @FXML
    public void stationCard()
    {
        if(section.getStationedCards().size() < AdventureConstants.MAX_STATIONS) {
            CardChooserDialog chooserDialog = new CardChooserDialog();
            chooserDialog.initialize(mainDatabase, adventure.getTeamCards(), TargetType.CARD);
            Optional<Card> cardSelect = chooserDialog.showAndWait();
            cardSelect.ifPresent(card -> adventure.stationCard(section, card));
            initializeStations(section);
            controlPane.refreshToMatch();
        }
    }

    @FXML
    public void createDeck()
    {
        DeckConstructorPane deckConstructorPane = new DeckConstructorPane();
        deckConstructorPane.initialize(mainDatabase, sectionViewPane, adventure);
        changeScene(deckConstructorPane);
    }
}
