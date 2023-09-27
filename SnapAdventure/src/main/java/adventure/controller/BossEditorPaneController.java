package adventure.controller;

import adventure.controller.manager.AdvEditorPaneController;
import adventure.model.AdvControllerDatabase;
import adventure.model.Boss;
import adventure.view.node.BossEditorNode;
import adventure.view.pane.AdvCardManagerPane;
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

    public void initialize(AdvControllerDatabase database, Boss b)
    {
        super.initialize(database);
        bossEditorNode.initialize(database, b);
    }

    @FXML
    private void saveBoss()
    {
        Boss b = bossEditorNode.generateBoss();
        controllerDatabase.modifyBoss(b);
        AdvCardManagerPane cardManagerPane = new AdvCardManagerPane();
        cardManagerPane.initialize(controllerDatabase);
        changeScene(cardManagerPane);
    }

    @Override
    public void initializeButtonToolBar() {
        AdvCardManagerPane cardManagerPane = new AdvCardManagerPane();
        cardManagerPane.initialize(controllerDatabase);
        buttonToolBar.initialize(cardManagerPane);
    }
}
