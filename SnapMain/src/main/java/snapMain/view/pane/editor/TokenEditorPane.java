package snapMain.view.pane.editor;

import snapMain.controller.MainDatabase;
import snapMain.controller.editor.TokenEditorPaneController;
import snapMain.model.target.Token;
import snapMain.view.ViewSize;
import snapMain.view.fxml.FXMLMainGrabber;

public class TokenEditorPane extends EditorPane {

    TokenEditorPaneController controller;
    public TokenEditorPane()
    {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("tokenEditorPane.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(MainDatabase database, ViewSize v, Token t)
    {
        controller.initialize(database, v, t);
    }
}
