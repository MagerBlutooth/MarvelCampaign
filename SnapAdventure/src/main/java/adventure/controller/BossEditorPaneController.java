package adventure.controller;

import adventure.controller.manager.AdvEditorPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.Boss;
import adventure.view.node.BossEditorNode;
import adventure.view.pane.AdvBossManagerPane;
import campaign.model.thing.ThingType;
import campaign.view.grabber.ThingImageGrabber;
import javafx.fxml.FXML;

public class BossEditorPaneController extends AdvEditorPaneController
{
    ThingImageGrabber imageGrabber;
    @FXML
    BossEditorNode bossEditorNode;

    public BossEditorPaneController()
    {
        imageGrabber = new ThingImageGrabber(ThingType.CARD);
    }

    public void initialize(AdvMainDatabase database, Boss b)
    {
        super.initialize(database);
        bossEditorNode.initialize(database, b);
    }

    @FXML
    private void saveBoss()
    {
        Boss b = bossEditorNode.generateBoss();
        mainDatabase.modifyBoss(b);
        AdvBossManagerPane cardManagerPane = new AdvBossManagerPane();
        cardManagerPane.initialize(mainDatabase);
        changeScene(cardManagerPane);
    }

    @Override
    public void initializeButtonToolBar() {
        AdvBossManagerPane cardManagerPane = new AdvBossManagerPane();
        cardManagerPane.initialize(mainDatabase);
        buttonToolBar.initialize(cardManagerPane);
    }
}
