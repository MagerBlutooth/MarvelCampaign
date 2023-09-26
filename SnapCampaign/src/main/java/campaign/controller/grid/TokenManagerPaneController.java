package campaign.controller.grid;

import campaign.controller.ControllerDatabase;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import campaign.model.thing.ThingType;
import campaign.model.thing.Token;
import campaign.model.thing.TokenList;
import campaign.view.ViewSize;
import campaign.view.manager.TokenManager;
import campaign.view.node.control.ControlNode;
import campaign.view.pane.editor.TokenEditorPane;


public class TokenManagerPaneController extends ManagerPaneController<Token, ControllerDatabase> {
    @FXML
    TokenManager tokenManager;

    @Override
    public Scene getCurrentScene() {
        return tokenManager.getScene();
    }

    @Override
    public void initialize(ControllerDatabase m) {
        super.initialize(m);
        tokenManager.initialize(new TokenList<>(m.getTokens()), ThingType.TOKEN,this, ViewSize.MEDIUM, true);
    }

    @Override
    public void saveGridNode(ControlNode<Token> node) {
        controllerDatabase.saveDatabase(node.getSubject().getThingType());
    }

    @Override
    public void editSubject(ControlNode<Token> node) {
        TokenEditorPane tokenEditorPane = new TokenEditorPane();
        tokenEditorPane.initialize(controllerDatabase, ViewSize.LARGE, (Token)node.getSubject());
        changeScene(tokenEditorPane);
    }

    @FXML
    public void addNewEntry()
    {
        TokenEditorPane tokenEditorPane = new TokenEditorPane();
        tokenEditorPane.initialize(controllerDatabase, ViewSize.LARGE, new Token());
        changeScene(tokenEditorPane);
    }
}
