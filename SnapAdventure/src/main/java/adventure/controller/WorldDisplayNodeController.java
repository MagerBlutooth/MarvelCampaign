package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.World;
import adventure.model.thing.Enemy;
import adventure.model.thing.BossSection;
import adventure.model.thing.Section;
import adventure.view.node.EnemyControlNode;
import adventure.view.node.SectionControlNode;
import adventure.view.pane.AdventureControlPane;
import adventure.view.pane.BossViewPane;
import adventure.view.pane.SectionViewPane;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import snapMain.view.ViewSize;

import java.util.ArrayList;
import java.util.List;

public class WorldDisplayNodeController extends AdvPaneController {

    @FXML
    EnemyControlNode bossControlNode;
    @FXML
    Label bossEffectText;
    @FXML
    SectionControlNode section1Node;
    @FXML
    SectionControlNode section2Node;
    @FXML
    SectionControlNode section3Node;
    @FXML
    SectionControlNode section4Node;
    @FXML
    Label worldLabel;
    List<SectionControlNode> sections;

    World world;


    public void initialize(AdvMainDatabase d, World w, AdventureControlPane aPane)
    {
        mainDatabase = d;
        world = w;
        setWorldLabel(w);
        sections = new ArrayList<>();

        Section advLocation1 = w.getFirstSection();
        Section advLocation2 = w.getSecondSection();
        Section advLocation3 = w.getThirdSection();
        Section advLocation4 = w.getFourthSection();
        Enemy boss = w.getBoss();
        bossEffectText.setText(boss.getEffect());
        section1Node.initialize(d, advLocation1, d.grabImage(advLocation1.getLocation()),
                ViewSize.MEDIUM, true);
        section2Node.initialize(d, advLocation2, d.grabImage(advLocation1.getLocation()),
                ViewSize.MEDIUM, false);
        section3Node.initialize(d, advLocation3, d.grabImage(advLocation1.getLocation()),
                ViewSize.MEDIUM, false);
        section4Node.initialize(d, advLocation4, d.grabImage(advLocation1.getLocation()),
                ViewSize.MEDIUM, false);
        sections.add(section1Node);
        sections.add(section2Node);
        sections.add(section3Node);
        sections.add(section4Node);
        bossControlNode.initialize(mainDatabase, boss, d.grabImage(boss.getSubject()), ViewSize.MEDIUM, false);
        bossControlNode.unreveal();
        setSectionMouseOption(section1Node, aPane);
        setSectionMouseOption(section2Node, aPane);
        setSectionMouseOption(section3Node, aPane);
        setSectionMouseOption(section4Node, aPane);
        setBossMouseOption(bossControlNode, aPane);
    }

    private void setBossMouseOption(EnemyControlNode bossNode, AdventureControlPane aPane) {
        bossNode.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton() == MouseButton.PRIMARY && world.isBossRevealed()) {
                BossViewPane bossViewPane = new BossViewPane();
                bossViewPane.initialize(mainDatabase, aPane, new BossSection(aPane.getAdventureDatabase(),
                        bossNode.getSubject()));
                changeScene(bossViewPane);
            }
        });
    }

    private void setSectionMouseOption(SectionControlNode sectionNode, AdventureControlPane aPane) {
        sectionNode.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton() == MouseButton.PRIMARY && sectionNode.isRevealed()) {
                SectionViewPane sectionViewPane = new SectionViewPane();
                sectionViewPane.initialize(mainDatabase, aPane, sectionNode.getSubject());
                changeScene(sectionViewPane);
            }
        });
    }

    public void revealNextSection(int currentSectionNum)
    {
        world.revealNextSection(currentSectionNum);
        refresh(world);
    }

    public void refresh(World w)
    {
        setWorldLabel(w);
        section1Node.refresh(w.getFirstSection());
        section2Node.refresh(w.getSecondSection());
        section3Node.refresh(w.getThirdSection());
        section4Node.refresh(w.getFourthSection());
        bossControlNode.refresh((w.getBoss()));
    }

    private void setWorldLabel(World w) {
        worldLabel.setText("World " + w.getWorldNum());
    }

    @Override
    public Scene getCurrentScene() {
        return section1Node.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
    }

    public void revealBossCheck() {
        if(world.isBossRevealed())
            bossControlNode.reveal();
    }
}
