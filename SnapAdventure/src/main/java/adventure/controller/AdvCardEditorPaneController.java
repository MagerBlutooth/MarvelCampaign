package adventure.controller;

import adventure.controller.manager.AdvEditorPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.target.AdvCard;
import adventure.view.node.AdvCardEditorNode;
import adventure.view.pane.AdvCardManagerPane;
import javafx.fxml.FXML;
import snapMain.model.target.TargetType;
import snapMain.view.grabber.TargetImageGrabber;

public class AdvCardEditorPaneController extends AdvEditorPaneController
{
    TargetImageGrabber imageGrabber;
    @FXML
    AdvCardEditorNode advCardEditorNode;

    public AdvCardEditorPaneController()
    {
        imageGrabber = new TargetImageGrabber(TargetType.CARD);
    }

    public void initialize(AdvMainDatabase database, AdvCard b)
    {
        super.initialize(database);
        advCardEditorNode.initialize(database, b);
    }

    @FXML
    public void saveBoss()
    {
        AdvCard b = advCardEditorNode.generateBoss();
        mainDatabase.modifyBoss(b);
        AdvCardManagerPane cardManagerPane = new AdvCardManagerPane();
        cardManagerPane.initialize(mainDatabase);
        changeScene(cardManagerPane);
    }

    @Override
    public void initializeButtonToolBar() {
        AdvCardManagerPane cardManagerPane = new AdvCardManagerPane();
        cardManagerPane.initialize(mainDatabase);
        buttonToolBar.initialize(cardManagerPane);
    }
}
