package campaign.controller.editor;

import campaign.controller.ControllerDatabase;
import javafx.fxml.FXML;
import campaign.model.thing.ThingType;
import campaign.model.thing.Token;
import campaign.view.ViewSize;
import campaign.view.grabber.ThingImageGrabber;
import campaign.view.node.editor.TokenEditorNode;
import campaign.view.pane.manager.TokenManagerPane;
import campaign.view.thing.TokenView;

public class TokenEditorPaneController extends EditorPaneController {

    @FXML
    TokenView imagePane;

    @FXML
    TokenEditorNode tokenEditorNode;

    ThingImageGrabber imageGrabber;

    public TokenEditorPaneController()
    {
        imageGrabber = new ThingImageGrabber(ThingType.TOKEN);
    }

    @Override
    public void initializeButtonToolBar() {
        TokenManagerPane tokenManagerPane = new TokenManagerPane();
        tokenManagerPane.initialize(controllerDatabase);
        buttonToolBar.initialize(tokenManagerPane);
    }

    public void initialize(ControllerDatabase database, ViewSize v, Token t)
    {
        super.initialize(database);
        tokenEditorNode.initialize(database, t);
        imagePane.initialize(t, v, true);
    }

    @FXML
    private void saveToken()
    {
        Token t = tokenEditorNode.generateToken();
        getDatabase().addToken(t, imagePane.getImage());
        imageGrabber.saveImage(imagePane.getImage(), t.getID());
        TokenManagerPane tokenManagerPane = new TokenManagerPane();
        tokenManagerPane.initialize(controllerDatabase);
        changeScene(tokenManagerPane);
    }

}
