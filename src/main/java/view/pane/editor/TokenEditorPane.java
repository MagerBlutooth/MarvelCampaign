package view.pane.editor;

import controller.ControllerDatabase;
import controller.editor.TokenEditorPaneController;
import model.thing.Token;
import view.ViewSize;
import view.fxml.FXMLGrabber;

public class TokenEditorPane extends EditorPane {

    TokenEditorPaneController controller;
    public TokenEditorPane()
    {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("tokenEditorPane.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase database, ViewSize v, Token t)
    {
        controller.initialize(database, v, t);
    }
}
