package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.thing.AdvLocation;
import adventure.model.thing.Section;
import adventure.view.node.AdvLocationControlNode;
import adventure.view.pane.AdventureControlPane;
import adventure.view.popup.CardChooserDialog;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import snapMain.controller.grid.BaseGridActionController;
import snapMain.model.target.Card;
import snapMain.model.target.Playable;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.button.ButtonToolBar;
import snapMain.view.node.GridDisplayNode;

import java.util.Optional;

public class SectionViewPaneController extends AdvPaneController {

    @FXML
    StackPane stationedDisplayBox;
    @FXML
    StackPane pickupDisplayBox;
    @FXML
    ButtonToolBar buttonToolBar;
    @FXML
    AdvLocationControlNode sectionView;
    @FXML
    Label effectText;
    @FXML
    Button skipButton;
    @FXML
    Button completeButton;
    @FXML
    Button randomizeButton;
    AdventureControlPane controlPane;
    Section section;
    Adventure adventure;
    GridDisplayNode<Card> stationedDisplay;

    public void initialize(AdvMainDatabase dB, AdventureControlPane cP, Section s) {
        mainDatabase = dB;
        controlPane = cP;
        section = s;
        adventure = cP.getAdventure();
        AdvLocation l = s.getLocation();
        sectionView.initialize(mainDatabase, s.getLocation(), mainDatabase.grabImage(s.getLocation()),
                ViewSize.LARGE, s.isRevealed());
        effectText.setText(l.getEffect());
        effectText.setMouseTransparent(true);
        effectText.setFocusTraversable(false);
        initializePickups(s);
        initializeStations(s);
        initializeButtonToolBar();

    }

    private void initializeStations(Section s) {
        if(s.hasStationedCards()) {
            stationedDisplay = new GridDisplayNode<>();
            BaseGridActionController<Card> gridActionController = new BaseGridActionController<>();
            gridActionController.initialize(mainDatabase);
             stationedDisplay.initialize(s.getStationedCards(), TargetType.CARD_OR_TOKEN, gridActionController,
                    ViewSize.TINY, false);
            stationedDisplayBox.getChildren().add(stationedDisplay);
        }
    }

    private void initializePickups(Section s) {
        if(s.hasPickups()) {
            GridDisplayNode<Playable> pickupDisplay = new GridDisplayNode<>();
            BaseGridActionController<Playable> gridActionController = new BaseGridActionController<>();
            gridActionController.initialize(mainDatabase);
            pickupDisplay.initialize(s.getPickups(), TargetType.CARD_OR_TOKEN, gridActionController,
                    ViewSize.TINY, false);
            pickupDisplayBox.getChildren().add(pickupDisplay);
        }
    }

    @Override
    public Scene getCurrentScene() {
        return sectionView.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
        buttonToolBar.initialize(controlPane);
    }

    public void randomize()
    {
        adventure.randomizeSection(section);
        sectionView.update(section.getLocation());
        effectText.setText(section.getEffect());
        controlPane.refreshToMatch();
    }

    public void completeSection()
    {
        section.complete();
        controlPane.completeCurrentSection();
        changeScene(controlPane);
    }

    public void skipSection()
    {
        controlPane.skipSection(section);
        changeScene(controlPane);
    }

    public void stationCard()
    {
        CardChooserDialog chooserDialog = new CardChooserDialog();
        chooserDialog.initialize(mainDatabase, adventure.getActiveCards(), TargetType.CARD);
        Optional<Card> cardSelect = chooserDialog.showAndWait();
        cardSelect.ifPresent(card -> adventure.stationCard(section, card));
        initializeStations(section);
        controlPane.refreshToMatch();
    }
}
