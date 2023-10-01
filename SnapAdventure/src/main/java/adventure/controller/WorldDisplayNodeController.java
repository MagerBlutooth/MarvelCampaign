package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.World;
import adventure.model.thing.Boss;
import adventure.model.thing.Section;
import adventure.view.node.BossControlNode;
import adventure.view.node.SectionControlNode;
import adventure.view.pane.AdventureControlPane;
import adventure.view.pane.SectionViewPane;
import javafx.scene.Scene;
import snapMain.model.thing.TargetType;
import snapMain.view.ViewSize;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class WorldDisplayNodeController extends AdvPaneController {

    @FXML
    SectionControlNode section1Node;
    @FXML
    SectionControlNode section2Node;
    @FXML
    SectionControlNode section3Node;
    @FXML
    SectionControlNode section4Node;
    @FXML
    BossControlNode bossNode;
    @FXML
    Label worldLabel;

    World world;
    List<SectionControlNode> sections;

    AdvMainDatabase database;

    int worldNum;
    int sectionNum;

    public void initialize(AdvMainDatabase d, World w, int wNum, int sNum, AdventureControlPane aPane)
    {
        database = d;
        world = w;
        worldNum = wNum;
        sectionNum = sNum;
        worldLabel.setText("World "+ worldNum);
        sections = new ArrayList<>();

        Section advLocation1 = w.getFirstSection();
        Section advLocation2 = w.getSecondSection();
        Section advLocation3 = w.getThirdSection();
        Section advLocation4 = w.getFourthSection();
        Boss boss = w.getBoss();
        section1Node.initialize(d, advLocation1, d.grabImage(advLocation1.getLocation(), TargetType.LOCATION),
                ViewSize.MEDIUM, true);
        section2Node.initialize(d, advLocation2, d.grabImage(advLocation1.getLocation(), TargetType.LOCATION),
                ViewSize.MEDIUM, false);
        section3Node.initialize(d, advLocation3, d.grabImage(advLocation1.getLocation(), TargetType.LOCATION),
                ViewSize.MEDIUM, false);
        section4Node.initialize(d, advLocation4, d.grabImage(advLocation1.getLocation(), TargetType.LOCATION),
                ViewSize.MEDIUM, false);
        sections.add(section1Node);
        sections.add(section2Node);
        sections.add(section3Node);
        sections.add(section4Node);
        setSectionMouseOption(section1Node, aPane);
        setSectionMouseOption(section2Node, aPane);
        setSectionMouseOption(section3Node, aPane);
        setSectionMouseOption(section4Node, aPane);
        bossNode.initialize(d, boss.getCard(), d.grabImage(boss.getCard(), TargetType.CARD), ViewSize.MEDIUM, false);
    }

    private void setSectionMouseOption(SectionControlNode sectionNode, AdventureControlPane aPane) {
        sectionNode.setOnMouseClicked(mouseEvent -> {
            SectionViewPane sectionViewPane = new SectionViewPane();
            sectionViewPane.initialize(database, aPane, sectionNode.getSubject());
            changeScene(sectionViewPane);
        });
    }

    public void revealNextSection()
    {
        SectionControlNode node = sections.get(sectionNum);
        node.reveal();
    }

    public void revealBoss()
    {
        bossNode.reveal();
    }

    public void refresh()
    {

    }

    @Override
    public Scene getCurrentScene() {
        return bossNode.getScene();
    }

    @Override
    public void initializeButtonToolBar() {

    }
}
