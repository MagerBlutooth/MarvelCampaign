package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureConstants;
import adventure.model.AdventureDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.target.*;
import adventure.model.target.base.*;
import adventure.view.node.AdvLocationControlNode;
import adventure.view.node.EnemyControlNode;
import adventure.view.node.HPDisplayNode;
import adventure.view.pane.AdventureClearPane;
import adventure.view.pane.AdventureControlPane;
import adventure.view.pane.DeckConstructorPane;
import adventure.view.pane.WorldClearPane;
import adventure.view.popup.*;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import snapMain.controller.grid.BaseGridActionController;
import snapMain.model.database.TargetDatabase;
import snapMain.model.logger.MLogger;
import snapMain.model.target.Playable;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.pane.FullViewPane;

import java.util.Optional;

public class SectionViewPaneController extends FullViewPaneController {

    @FXML
    FullViewPane sectionViewPane;
    @FXML
    Label enemyEffectText;
    @FXML
    Label secondaryEffectText;
    @FXML
    EnemyControlNode enemyView;
    @FXML
    HPDisplayNode hpDisplay;
    @FXML
    StackPane stationedDisplayBox;
    @FXML
    StackPane rewardDisplayBox;
    @FXML
    AdvLocationControlNode locationView;
    @FXML
    Label locationEffectText;
    @FXML
    Button skipButton;
    @FXML
    Button completeButton;
    AdventureControlPane adventureControlPane;
    Section section;
    Adventure adventure;
    GridDisplayNode<ActiveCard> stationedDisplay;
    Enemy enemy;
    ContextMenu enemyMenu;

    MLogger logger = new MLogger(SectionViewPaneController.class);

    public void initialize(AdvMainDatabase dB, AdventureControlPane cP, Section s) {
        mainDatabase = dB;
        adventureControlPane = cP;
        section = s;
        if (s instanceof BossSection)
            skipButton.setDisable(true);
        enemy = s.getEnemy();
        adventure = cP.getAdventure();
        AdvLocation l = s.getLocation();
        locationView.initialize(mainDatabase, s.getLocation(), mainDatabase.grabImage(s.getLocation()),
                ViewSize.LARGE, s.isRevealed());
        locationEffectText.setText(l.getEffect());
        enemyEffectText.setText(enemy.getEffect());
        enemyView.initialize(mainDatabase, enemy, mainDatabase.grabImage(enemy.getSubject()),
                ViewSize.MEDIUM, false);
        setSecondaryEffect();
        hpDisplay.initialize(enemy);
        completeButton.disableProperty().bind(Bindings.lessThan(0, hpDisplay.getHPProperty()));
        initializeRewards(s);
        initializeStations(s);
        initializeContextMenus();
    }

    private void setSecondaryEffect() {
        if (enemy.getSecondarySubject().isActualThing()) {
            enemyView.setSecondary(enemy.getSecondarySubject());
            secondaryEffectText.setText(enemy.getSecondaryEffect());
        }
    }

    private void initializeContextMenus() {
        ContextMenu sectionMenu = new ContextMenu();
        MenuItem changeItem = new MenuItem("Change");
        changeItem.setOnAction(e -> changeLocation());
        MenuItem destroyItem = new MenuItem("Destroy");
        destroyItem.setOnAction(e -> destroyLocation());
        sectionMenu.getItems().add(changeItem);
        sectionMenu.getItems().add(destroyItem);
        locationView.setOnMouseClicked(e -> sectionMenu.show(locationView, e.getScreenX(), e.getScreenY()));

        enemyMenu = new ContextMenu();
        MenuItem changeHPItem = new MenuItem("Change Base HP");
        changeHPItem.setOnAction(e -> changeBaseHP());
        MenuItem changeEnemy = new MenuItem("Change Enemy");
        changeEnemy.setOnAction(e -> changeEnemy());
        enemyMenu.getItems().add(changeHPItem);
        enemyMenu.getItems().add(changeEnemy);
        MenuItem changeEnemyClone = new MenuItem("Change Enemy to Clone");
        changeEnemyClone.setOnAction(e -> changeEnemyClone());
        enemyMenu.getItems().add(changeEnemyClone);
        MenuItem addSecondaryEffectIem = new MenuItem("Add Secondary Effect");
        addSecondaryEffectIem.setOnAction(e -> addSecondaryEffect());
        enemyMenu.getItems().add(addSecondaryEffectIem);
        enemyView.setOnMouseClicked(e -> enemyMenu.show(enemyView, e.getScreenX(), e.getScreenY()));
        MenuItem escapeEnemyOption = new MenuItem("Enemy Escapes");
        escapeEnemyOption.setOnAction(e -> enemyEscape());
        enemyMenu.getItems().add(escapeEnemyOption);
        if (adventure.hasInfinityStone()) {
            MenuItem stealInfinityStoneOption = new MenuItem("Enemy Steals Infinity Stone");
            stealInfinityStoneOption.setOnAction(e -> enemyStealsInfinityStone(section));
            enemyMenu.getItems().add(stealInfinityStoneOption);
        }
        initializeStationContext();

    }

    private void initializeStationContext() {
        ContextMenu stationMenu = new ContextMenu();
        MenuItem stationCardItem = new MenuItem("Station");
        stationMenu.getItems().add(stationCardItem);
        stationCardItem.setOnAction(e -> stationCard());
        if (!stationedDisplay.isEmpty()) {
            MenuItem unstationCardItem = new MenuItem("Unstation");
            unstationCardItem.setOnAction(e -> unstationCard());
            stationMenu.getItems().add(unstationCardItem);
        }
        stationedDisplayBox.setOnMouseClicked(e -> stationMenu.show(stationedDisplayBox, e.getScreenX(),
                e.getScreenY()));
    }

    private void enemyStealsInfinityStone(Section s) {
        adventure.stealRandomInfinityStone(s);
        adventureControlPane.refreshToMatch();
    }

    private void enemyEscape() {
        adventure.enemyEscapes(section.getSectionNum());
        enemyView.refresh(enemy, true);
        enemyEffectText.setText(enemy.getEffect());
        secondaryEffectText.setText(enemy.getSecondaryEffect());
    }

    private void addSecondaryEffect() {
        CardOrTokenSearchSelectDialog chooserDialog = new CardOrTokenSearchSelectDialog();
        AdventureDatabase aDatabase = adventure.getAdventureDatabase();
        TargetList<Playable> playableOptions = new PlayableList(aDatabase.getEnemySubjects());
        chooserDialog.initialize(mainDatabase, playableOptions, "Choose Secondary Effect",
                getCurrentScene().getWindow());
        Optional<Playable> secondEffect = chooserDialog.showAndWait();
        if (secondEffect.isPresent()) {
            Playable enemyEffect = secondEffect.get();
            enemy.setSecondarySubject(enemyEffect);
            setSecondaryEffect();
            secondaryEffectText.setText(enemy.getSecondaryEffect());
        }
        refocusWindow();
    }

    private void changeEnemyClone() {
        AdventureDatabase adb = adventure.getAdventureDatabase();
        CardOrTokenSearchSelectDialog cardSearchSelectDialog = new CardOrTokenSearchSelectDialog();
        cardSearchSelectDialog.initialize(mainDatabase, new PlayableList(adb.getEnemySubjects()),
                "Make enemy clone of which card?", getCurrentScene().getWindow());
        Optional<Playable> playable = cardSearchSelectDialog.showAndWait();
        if (playable.isPresent()) {
            Playable p = playable.get();
            Enemy enemy = adventure.replaceEnemy(p, section.getSectionNum(), true);
            enemyView.refresh(enemy, true);
            enemyEffectText.setText(p.getEffect());
        }
        refocusWindow();
        adventureControlPane.refreshToMatch();
    }

    private void changeEnemy() {
        CardSearchSelectDialog cardSearchSelectDialog = new CardSearchSelectDialog();
        cardSearchSelectDialog.initialize(mainDatabase, new ActiveCardList(adventure.getFreeAgents()),
                "Choose new enemy", getCurrentScene().getWindow());
        Optional<ActiveCard> card = cardSearchSelectDialog.showAndWait();
        if (card.isPresent()) {
            ActiveCard c = card.get();
            TargetDatabase<AdvCard> bosses = mainDatabase.lookupDatabase(TargetType.ADV_CARD);
            AdvCard boss = bosses.lookup(c.getID());
            Enemy enemy = adventure.replaceEnemy(boss, section.getSectionNum(), false);
            enemyView.refresh(enemy, true);
            enemyEffectText.setText(boss.getEffect());
        }
        refocusWindow();
        adventureControlPane.refreshToMatch();
    }

    public void goBack() {
        changeScene(adventureControlPane);
    }

    private void changeLocation() {
        AdvLocationSearchSelectDialog locationSearchSelectDialog = new AdvLocationSearchSelectDialog();
        AdvLocationList selectableLocations = new AdvLocationList(adventure.getAvailableLocations());
        if (!selectableLocations.contains(AdventureConstants.LIMBO_ID)) {
            TargetDatabase<AdvLocation> locs = mainDatabase.lookupDatabase(TargetType.LOCATION);
            AdvLocation l = locs.lookup(AdventureConstants.LIMBO_ID);
            selectableLocations.add(l);
        }

        locationSearchSelectDialog.initialize(mainDatabase, selectableLocations,
                "Choose new location", getCurrentScene().getWindow());
        Optional<AdvLocation> location = locationSearchSelectDialog.showAndWait();
        if (location.isPresent() && location.get().isActualThing()) {
            AdvLocation newLoc = location.get();
            adventure.updateSection(newLoc, section.getSectionNum());
            locationView.update(newLoc);
            locationEffectText.setText(newLoc.getEffect());
        }
        refocusWindow();
        adventureControlPane.refreshToMatch();
    }

    private void destroyLocation() {
        AdvLocation destroyedLoc = new Ruins();
        adventure.destroySection(section.getSectionNum());
        locationView.update(destroyedLoc);
        locationEffectText.setText(destroyedLoc.getEffect());
    }

    private void initializeStations(Section s) {
        stationedDisplay = new GridDisplayNode<>();
        BaseGridActionController<ActiveCard> gridActionController = new BaseGridActionController<>();
        gridActionController.initialize(mainDatabase);
        stationedDisplay.initialize(s.getStationedCards(), TargetType.CARD, gridActionController,
                ViewSize.TINY, false);
        stationedDisplay.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        stationedDisplay.setMinHeight(120);
        stationedDisplayBox.getChildren().add(stationedDisplay);
    }

    private void initializeRewards(Section s) {
        GridDisplayNode<Playable> pickupDisplay = new GridDisplayNode<>();
        BaseGridActionController<Playable> gridActionController = new BaseGridActionController<>();
        gridActionController.initialize(mainDatabase);
        pickupDisplay.initialize(s.getRewards(), TargetType.CARD_OR_TOKEN, gridActionController,
                ViewSize.TINY, false);
        pickupDisplay.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pickupDisplay.setMinHeight(120);
        rewardDisplayBox.getChildren().add(pickupDisplay);
    }

    @Override
    public Scene getCurrentScene() {
        return enemyView.getScene();
    }

    @Override
    public void initializeButtonToolBar() {

    }

    @FXML
    public void completeSection() {
        section.complete();
        adventure.collectPickups(section);
        if (!(section instanceof BossSection)) {
            adventureControlPane.completeCurrentSection();
            defeatEnemy();
            changeScene(adventureControlPane);
        } else {
            logger.info(enemy + " defeated!");
            defeatBoss();
        }
    }

    private void defeatEnemy() {
        if(enemy.getObtainableCard() != null) {
            adventure.addCardToTeam(new ActiveCard(enemy.getObtainableCard()));
        }
        section.setEnemy(adventure.createNewMook());
    }

    @FXML
    public void skipSection() {
        adventureControlPane.skipSection(section);
        changeScene(adventureControlPane);
    }

    private void defeatBoss() {
        WorldClearPane worldClearPane = new WorldClearPane();
        worldClearPane.initialize(mainDatabase, adventure, adventureControlPane);
        changeScene(worldClearPane);
    }

    @FXML
    public void changeBaseHP() {
        HPDialog dialog = new HPDialog();
        dialog.initialize(enemy.getBaseHP());
        Optional<Integer> newHP = dialog.showAndWait();
        newHP.ifPresent(integer -> {
            enemy.setBaseHP(integer);
            hpDisplay.update();
        });
        refocusWindow();
        adventure.saveAdventure();
    }

    @FXML
    public void stationCard() {
        if (section.getStationedCards().size() < AdventureConstants.MAX_STATIONS) {
            CardChooserDialog chooserDialog = new CardChooserDialog();
            chooserDialog.initialize(mainDatabase, adventure.getTeamCards(), TargetType.CARD,
                    "Station which card?", getCurrentScene().getWindow());
            Optional<ActiveCard> cardSelect = chooserDialog.showAndWait();
            cardSelect.ifPresent(card -> {
                adventure.stationCard(section, card);
                initializeStations(section);
                adventureControlPane.refreshToMatch();
                initializeStationContext();
            });
            refocusWindow();
        }
    }

    private void unstationCard() {
        SimpleChooserDialog<ActiveCard> chooserDialog = new SimpleChooserDialog<>();
        chooserDialog.initialize(mainDatabase, stationedDisplay.getThings(), adventure.getTeamCards(), TargetType.CARD);
        Optional<ActiveCard> chosenCard = chooserDialog.showAndWait();
        chosenCard.ifPresent(activeCard -> {
            adventure.unstationCard(section, activeCard);
            initializeStations(section);
            adventureControlPane.refreshToMatch();
            initializeStationContext();
        });
        refocusWindow();
    }

    @FXML
    public void createDeck() {
        DeckConstructorPane deckConstructorPane = new DeckConstructorPane();
        deckConstructorPane.initialize(mainDatabase, sectionViewPane, adventure);
        changeScene(deckConstructorPane);
    }

    @FXML
    public void changeScene(FullViewPane pane) {
        adventureControlPane.refreshToMatch();
        super.changeScene(pane);
    }
}
