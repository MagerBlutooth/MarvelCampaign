package controller.grid;

import controller.ControllerDatabase;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import model.thing.ThingType;
import model.thing.Token;
import model.thing.TokenList;
import view.ViewSize;
import view.manager.TokenManager;
import view.node.control.ControlNode;
import view.pane.editor.TokenEditorPane;


public class TokenManagerPaneController extends ManagerPaneController<Token> {
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
