package snapMain.controller.editor;

import snapMain.controller.MainDatabase;
import javafx.fxml.FXML;
import snapMain.model.target.TargetType;
import snapMain.model.target.Token;
import snapMain.view.ViewSize;
import snapMain.view.grabber.TargetImageGrabber;
import snapMain.view.node.editor.TokenEditorNode;
import snapMain.view.pane.manager.TokenManagerPane;
import snapMain.view.thing.TokenView;

public class TokenEditorPaneController extends EditorPaneController {

    @FXML
    TokenView imagePane;

    @FXML
    TokenEditorNode tokenEditorNode;

    TargetImageGrabber imageGrabber;

    public TokenEditorPaneController()
    {
        imageGrabber = new TargetImageGrabber(TargetType.TOKEN);
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
