package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.thing.AdvLocation;
import adventure.model.thing.Section;
import adventure.view.AdvTooltip;
import adventure.view.node.AdvLocationControlNode;
import adventure.view.pane.AdventureControlPane;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    Button skipButton;
    @FXML
    Button completeButton;
    @FXML
    Button randomizeButton;
    AdvMainDatabase database;
    AdventureControlPane controlPane;
    Section section;
    Adventure adventure;
    public void initialize(AdvMainDatabase dB, AdventureControlPane cP, Section s) {
        database = dB;
        controlPane = cP;
        section = s;
        adventure = cP.getAdventure();
        AdvLocation l = s.getLocation();
        sectionView.initialize(database, s.getLocation(), database.grabImage(s.getLocation(), TargetType.LOCATION), ViewSize.LARGE, s.isRevealed());
        effectText.setText(l.getEffect());
        effectText.setMouseTransparent(true);
        effectText.setFocusTraversable(false);
        initializeButtonToolBar();
        addTooltips();
    }

    private void addTooltips() {
        AdvTooltip skipTooltip = new AdvTooltip();
        skipTooltip.setText("Skip current section and advance to next. A random card on your team will be captured.");
        skipButton.setTooltip(skipTooltip);

        AdvTooltip completeTooltip = new AdvTooltip();
        completeTooltip.setText("Clear current section. Reveal next section.");
        completeButton.setTooltip(completeTooltip);

        AdvTooltip randomTooltip = new AdvTooltip();
        randomTooltip.setText("Randomize the current section.");
        randomizeButton.setTooltip(randomTooltip);
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
}
