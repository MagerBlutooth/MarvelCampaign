package controller.editor;

import controller.ControllerDatabase;
import javafx.fxml.FXML;
import model.thing.ThingType;
import model.thing.Token;
import view.ViewSize;
import view.grabber.ThingImageGrabber;
import view.node.editor.TokenEditorNode;
import view.pane.manager.CardManagerPane;
import view.pane.manager.TokenManagerPane;
import view.thing.TokenView;

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
