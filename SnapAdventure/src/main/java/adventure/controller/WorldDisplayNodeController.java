package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.World;
import adventure.model.target.Enemy;
import adventure.model.target.BossSection;
import adventure.model.target.Section;
import adventure.view.node.EnemyControlNode;
import adventure.view.node.SectionControlNode;
import adventure.view.pane.AdventureControlPane;
import adventure.view.pane.SectionViewPane;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import snapMain.view.ViewSize;

import java.util.ArrayList;
import java.util.List;

public class WorldDisplayNodeController extends AdvPaneController {

    @FXML
    EnemyControlNode bossControlNode;
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

    AdventureControlPane adventurePane;
    ContextMenu bossContextMenu;


    public void initialize(AdvMainDatabase d, World w, AdventureControlPane aPane)
    {
        mainDatabase = d;
        world = w;
        setWorldLabel(w);
        sections = new ArrayList<>();
        adventurePane = aPane;

        Section section1 = w.getFirstSection();
        Section section2 = w.getSecondSection();
        Section section3 = w.getThirdSection();
        Section section4 = w.getFourthSection();
        Enemy boss = w.getBoss();
        section1Node.initialize(d, section1, d.grabImage(section1.getLocation()),
                ViewSize.MEDIUM, w.isCurrentSection(section1));
        section2Node.initialize(d, section2, d.grabImage(section2.getLocation()),
                ViewSize.MEDIUM, w.isCurrentSection(section2));
        section3Node.initialize(d, section3, d.grabImage(section3.getLocation()),
                ViewSize.MEDIUM, w.isCurrentSection(section3));
        section4Node.initialize(d, section4, d.grabImage(section4.getLocation()),
                ViewSize.MEDIUM, w.isCurrentSection(section4));
        sections.add(section1Node);
        sections.add(section2Node);
        sections.add(section3Node);
        sections.add(section4Node);
        bossControlNode.initialize(mainDatabase, boss, d.grabImage(boss.getSubject()), ViewSize.MEDIUM, false);
        bossControlNode.setRevealed(w.isBossRevealed());
        setSectionMouseOption(section1Node, aPane);
        setSectionMouseOption(section2Node, aPane);
        setSectionMouseOption(section3Node, aPane);
        setSectionMouseOption(section4Node, aPane);
        setBossMouseOption(bossControlNode, aPane);
        setBossContextMenu();
    }

    private void setBossContextMenu() {
        bossContextMenu = new ContextMenu();
        MenuItem revealItem = setRevealOption();
        bossContextMenu.getItems().add(revealItem);
        bossControlNode.setOnContextMenuRequested(e -> bossContextMenu.show(bossControlNode, e.getScreenX(),
                e.getScreenY()));
    }

    private MenuItem setRevealOption() {
        MenuItem menuItem;
        if(world.isBossRevealed())
        {
            menuItem = new MenuItem("Unreveal");
            menuItem.setOnAction(e -> {
                unrevealBoss();
                setBossContextMenu();
            });
        }
        else
        {
            menuItem = new MenuItem("Reveal");
            menuItem.setOnAction(e -> {
                revealBoss();
                setBossContextMenu();
            });
        }
        return menuItem;
    }

    private void setBossMouseOption(EnemyControlNode bossNode, AdventureControlPane aPane) {
        bossNode.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton() == MouseButton.PRIMARY && world.isBossRevealed()) {
                SectionViewPane bossViewPane = new SectionViewPane();
                bossViewPane.initialize(mainDatabase, aPane, new BossSection(aPane.getAdventureDatabase(),
                        bossNode.getSubject()));
                changeScene(bossViewPane);
            }
        });
    }

    private void setSectionMouseOption(SectionControlNode sectionNode, AdventureControlPane aPane) {
        sectionNode.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton() == MouseButton.PRIMARY && world.isCurrentSection(sectionNode.getSubject())) {
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

    public void revealBoss()
    {
        world.setBossRevealed(true);
        bossControlNode.setRevealed(true);
        refresh(world);
    }

    public void unrevealBoss()
    {
        world.setBossRevealed(false);
        bossControlNode.setRevealed(false);
        refresh(world);
    }

    public void refresh(World w)
    {
        setWorldLabel(w);
        section1Node.refresh(w.getSection(1), w.isCurrentSection(w.getSection(1)));
        section2Node.refresh(w.getSection(2), w.isCurrentSection(w.getSection(2)));
        section3Node.refresh(w.getSection(3), w.isCurrentSection(w.getSection(3)));
        section4Node.refresh(w.getSection(4), w.isCurrentSection(w.getSection(4)));
        bossControlNode.refresh((w.getBoss()), w.isBossRevealed());
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
        if(world.isBossRevealed()) {
            bossControlNode.reveal();
            setRevealOption();
        }
    }
}
