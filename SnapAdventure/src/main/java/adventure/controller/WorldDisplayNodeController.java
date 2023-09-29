package adventure.controller;

import adventure.model.AdvControllerDatabase;
import adventure.model.Boss;
import adventure.model.Section;
import adventure.model.World;
import adventure.view.node.BossControlNode;
import adventure.view.node.SectionControlNode;
import campaign.model.thing.ThingType;
import campaign.view.ViewSize;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class WorldDisplayNodeController {

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

    AdvControllerDatabase database;

    int worldNum;

    public void initialize(AdvControllerDatabase d, World w, int num)
    {
        database = d;
        world = w;
        worldNum = num;
        worldLabel.setText("World "+ worldNum);
        sections = new ArrayList<>();

        int currentSection = world.getCurrentSectionNum();

        Section section1 = w.getFirstSection();
        Section section2 = w.getSecondSection();
        Section section3 = w.getThirdSection();
        Section section4 = w.getFourthSection();
        Boss boss = w.getBoss();
        section1Node.initialize(d, section1, d.grabImage(section1, ThingType.LOCATION), ViewSize.MEDIUM, true);
        section2Node.initialize(d, section2, d.grabImage(section2, ThingType.LOCATION), ViewSize.MEDIUM, false);
        section3Node.initialize(d, section3, d.grabImage(section3, ThingType.LOCATION), ViewSize.MEDIUM, false);
        section4Node.initialize(d, section4, d.grabImage(section4, ThingType.LOCATION), ViewSize.MEDIUM, false);
        sections.add(section1Node);
        sections.add(section2Node);
        sections.add(section3Node);
        sections.add(section4Node);

        for(int i = 0; i < currentSection; i++)
        {
            sections.get(i).reveal();
        }

        bossNode.initialize(d, boss, d.grabImage(boss, ThingType.CARD), ViewSize.MEDIUM, false);
    }

    public void revealNextSection()
    {
        SectionControlNode node = sections.get(world.getCurrentSectionNum());
        node.reveal();
    }

    public void revealBoss()
    {
        bossNode.reveal();
    }

    public void refresh()
    {

    }

}
