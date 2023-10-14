package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureDatabase;
import adventure.model.World;
import adventure.model.thing.AdvCard;
import adventure.model.thing.Enemy;
import adventure.model.thing.BossSection;
import adventure.model.thing.Section;
import adventure.view.node.EnemyControlNode;
import adventure.view.node.SectionControlNode;
import adventure.view.pane.AdventureControlPane;
import adventure.view.pane.SectionViewPane;
import adventure.view.popup.AdvCardChooserDialog;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import snapMain.view.ViewSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    AdventureControlPane adventurePane;
    ContextMenu bossContextMenu;


    public void initialize(AdvMainDatabase d, World w, AdventureControlPane aPane)
    {
        mainDatabase = d;
        world = w;
        setWorldLabel(w);
        sections = new ArrayList<>();
        adventurePane = aPane;

        Section advLocation1 = w.getFirstSection();
        Section advLocation2 = w.getSecondSection();
        Section advLocation3 = w.getThirdSection();
        Section advLocation4 = w.getFourthSection();
        Enemy boss = w.getBoss();
        bossEffectText.setText(boss.getEffect());
        section1Node.initialize(d, advLocation1, d.grabImage(advLocation1.getLocation()),
                ViewSize.MEDIUM, true);
        section2Node.initialize(d, advLocation2, d.grabImage(advLocation2.getLocation()),
                ViewSize.MEDIUM, false);
        section3Node.initialize(d, advLocation3, d.grabImage(advLocation3.getLocation()),
                ViewSize.MEDIUM, false);
        section4Node.initialize(d, advLocation4, d.grabImage(advLocation4.getLocation()),
                ViewSize.MEDIUM, false);
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
        MenuItem secondaryItem = getSecondaryMenuItem();
        bossContextMenu.getItems().add(secondaryItem);
        bossControlNode.setOnContextMenuRequested(e -> bossContextMenu.show(bossControlNode, e.getScreenX(),
                e.getScreenY()));
    }

    private MenuItem getSecondaryMenuItem() {
        MenuItem secondaryItem = new MenuItem("Add Secondary");
        secondaryItem.setOnAction(actionEvent -> {
            AdvCardChooserDialog chooserDialog = new AdvCardChooserDialog();
            AdventureDatabase aDatabase = adventurePane.getAdventureDatabase();
            chooserDialog.initialize(mainDatabase, aDatabase.getBossList());
            Optional<AdvCard> boss = chooserDialog.showAndWait();
            if(boss.isPresent())
            {
                if(bossControlNode.noSecondaryDefined()) {
                    MenuItem swapItem = new MenuItem("Swap Secondary");
                    swapItem.setOnAction(e ->
                    {
                        bossControlNode.swapPrimaryAndSecondary();
                        bossEffectText.setText(bossControlNode.getSubject().getEffect());
                    });
                    bossContextMenu.getItems().add(swapItem);
                }
                AdvCard bossCard = boss.get();
                bossControlNode.setSecondary(bossCard);
            }

        });
        return secondaryItem;
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
