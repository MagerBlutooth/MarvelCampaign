package snapMain.controller.editor;

import snapMain.controller.MainDatabase;
import javafx.fxml.FXML;
import snapMain.model.thing.TargetType;
import snapMain.model.thing.Token;
import snapMain.view.ViewSize;
import snapMain.view.grabber.ThingImageGrabber;
import snapMain.view.node.editor.TokenEditorNode;
import snapMain.view.pane.manager.TokenManagerPane;
import snapMain.view.thing.TokenView;

public class TokenEditorPaneController extends EditorPaneController {

    @FXML
    TokenView imagePane;

    @FXML
    TokenEditorNode tokenEditorNode;

    ThingImageGrabber imageGrabber;

    public TokenEditorPaneController()
    {
        imageGrabber = new ThingImageGrabber(TargetType.TOKEN);
    }

    @Override
    public void initializeButtonToolBar() {
        TokenManagerPane tokenManagerPane = new TokenManagerPane();
        tokenManagerPane.initialize(mainDatabase);
        buttonToolBar.initialize(tokenManagerPane);
    }

    public void initialize(MainDatabase database, ViewSize v, Token t)
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
        tokenManagerPane.initialize(mainDatabase);
        changeScene(tokenManagerPane);
    }

}
