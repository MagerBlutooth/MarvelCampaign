package adventure.controller;

import adventure.controller.manager.AdvEditorPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.thing.AdvCard;
import adventure.view.node.BossEditorNode;
import adventure.view.pane.AdvBossManagerPane;
import javafx.fxml.FXML;
import snapMain.model.target.TargetType;
import snapMain.view.grabber.ThingImageGrabber;

public class BossEditorPaneController extends AdvEditorPaneController
{
    ThingImageGrabber imageGrabber;
    @FXML
    BossEditorNode bossEditorNode;

    public BossEditorPaneController()
    {
        imageGrabber = new ThingImageGrabber(TargetType.CARD);
    }

    public void initialize(AdvMainDatabase database, AdvCard b)
    {
        super.initialize(database);
        bossEditorNode.initialize(database, b);
    }

    @FXML
    public void saveBoss()
    {
        AdvCard b = bossEditorNode.generateBoss();
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
