package snapMain.controller.grid;

import snapMain.controller.MainDatabase;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import snapMain.model.target.Card;
import snapMain.model.target.TargetType;
import snapMain.model.target.Token;
import snapMain.model.target.TokenList;
import snapMain.view.ViewSize;
import snapMain.view.manager.TokenManager;
import snapMain.view.node.control.ControlNode;
import snapMain.view.pane.editor.TokenEditorPane;


public class TokenManagerPaneController extends ManagerPaneController<Token, MainDatabase> {
    @FXML
    TokenManager tokenManager;

    @Override
    public Scene getCurrentScene() {
        return tokenManager.getScene();
    }

    @Override
    public void initialize(MainDatabase m) {
        super.initialize(m);
        TokenList tokenList = new TokenList(m.getTokens());
        tokenManager.initialize(tokenList, TargetType.TOKEN,this, ViewSize.MEDIUM, true);
    }

    @Override
    public ControlNode<Token> createEmptyNode(ViewSize v) {
        ControlNode<Token> tokenNode = new ControlNode<>();
        tokenNode.initialize(mainDatabase, new Token(), mainDatabase.grabBlankImage(TargetType.TOKEN),
                v,false);
        return tokenNode;
    }

    @Override
    public void saveGridNode(ControlNode<Token> node) {
        mainDatabase.saveDatabase(node.getSubject().getTargetType());
    }

    @Override
    public void editSubject(ControlNode<Token> node) {
        TokenEditorPane tokenEditorPane = new TokenEditorPane();
        tokenEditorPane.initialize(mainDatabase, ViewSize.LARGE, node.getSubject());
        changeScene(tokenEditorPane);
    }

    @FXML
    public void addNewEntry()
    {
        TokenEditorPane tokenEditorPane = new TokenEditorPane();
        tokenEditorPane.initialize(mainDatabase, ViewSize.LARGE, new Token());
        changeScene(tokenEditorPane);
    }
}
