package adventure.controller;

import adventure.controller.manager.AdvEditorPaneController;
import adventure.model.AdvControllerDatabase;
import adventure.model.Boss;
import adventure.view.node.AdvCardEditorNode;
import adventure.view.pane.AdvCardManagerPane;
import campaign.model.thing.Card;
import campaign.model.thing.ThingType;
import campaign.view.ViewSize;
import campaign.view.grabber.ThingImageGrabber;
import campaign.view.pane.manager.CardManagerPane;
import campaign.view.thing.CardView;
import javafx.fxml.FXML;

public class AdvCardEditorPaneController extends AdvEditorPaneController
{
    @FXML
    CardView imagePane;
    ThingImageGrabber imageGrabber;
    @FXML
    AdvCardEditorNode cardEditorNode;

    public AdvCardEditorPaneController()
    {
        imageGrabber = new ThingImageGrabber(ThingType.CARD);
    }

    public void initialize(AdvControllerDatabase database, ViewSize viewSize, Card c)
    {
        super.initialize(database);
        cardEditorNode.initialize(database, c);
        imagePane.initialize(controllerDatabase, c, viewSize, true);
        imagePane.disableTooltip();
    }

    @FXML
    private void saveBoss()
    {
        Boss b = cardEditorNode.generateBoss();
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
