package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.thing.AdvLocation;
import adventure.model.thing.Section;
import adventure.view.node.AdvLocationControlNode;
import adventure.view.pane.AdventureControlPane;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import snapMain.model.thing.PlayableList;
import snapMain.model.thing.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.button.ButtonToolBar;

public class SectionViewPaneController extends AdvPaneController {

    @FXML
    ButtonToolBar buttonToolBar;
    @FXML
    AdvLocationControlNode sectionView;
    @FXML
    Label effectText;
    AdvMainDatabase database;
    AdventureControlPane controlPane;
    public void initialize(AdvMainDatabase dB, AdventureControlPane cP, Section s) {
        database = dB;
        controlPane = cP;
        AdvLocation l = s.getLocation();
        PlayableList pickups = s.getPickups();
        sectionView.initialize(database, s.getLocation(), database.grabImage(s.getLocation(), TargetType.LOCATION), ViewSize.LARGE, s.isRevealed());
        effectText.setText(l.getEffect());
        effectText.setMouseTransparent(true);
        effectText.setFocusTraversable(false);
        initializeButtonToolBar();
    }

    @Override
    public Scene getCurrentScene() {
        return sectionView.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
        buttonToolBar.initialize(controlPane);
    }
}
