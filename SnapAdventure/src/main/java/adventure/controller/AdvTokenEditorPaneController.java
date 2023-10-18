package adventure.controller;

import adventure.controller.manager.AdvEditorPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.target.AdvToken;
import adventure.view.node.AdvTokenEditorNode;
import adventure.view.pane.AdvCardManagerPane;
import adventure.view.pane.AdvTokenManagerPane;
import javafx.fxml.FXML;
import snapMain.model.target.TargetType;
import snapMain.view.grabber.TargetImageGrabber;

public class AdvTokenEditorPaneController extends AdvEditorPaneController
{
    @FXML
    AdvTokenEditorNode advTokenEditorNode;
    TargetImageGrabber imageGrabber;

    public AdvTokenEditorPaneController()
    {
        imageGrabber = new TargetImageGrabber(TargetType.TOKEN);
    }

    public void initialize(AdvMainDatabase database, AdvToken t)
    {
        super.initialize(database);
        advTokenEditorNode.initialize(database, t);
    }

    @FXML
    public void saveToken()
    {
        AdvToken a = advTokenEditorNode.generateToken();
        mainDatabase.modifyAdvToken(a);
        AdvTokenManagerPane tokenManagerPane = new AdvTokenManagerPane();
        tokenManagerPane.initialize(mainDatabase);
        changeScene(tokenManagerPane);
    }

    @Override
    public void initializeButtonToolBar() {
        AdvTokenManagerPane tokenManagerPane = new AdvTokenManagerPane();
        tokenManagerPane.initialize(mainDatabase);
        buttonToolBar.initialize(tokenManagerPane);
    }
}
