package adventure.controller;

import adventure.model.AdvMainDatabase;
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

    AdvMainDatabase database;

    int worldNum;
    int sectionNum;

    public void initialize(AdvMainDatabase d, World w, int wNum, int sNum)
    {
        database = d;
        world = w;
        worldNum = wNum;
        sectionNum = sNum;
        worldLabel.setText("World "+ worldNum);
        sections = new ArrayList<>();

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

        for(int i = 0; i < sectionNum; i++)
        {
            sections.get(i).reveal();
        }

        bossNode.initialize(d, boss, d.grabImage(boss, ThingType.CARD), ViewSize.MEDIUM, false);
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

}
