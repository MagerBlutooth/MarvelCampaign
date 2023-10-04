package snapMain.view.pane.editor;

import snapMain.controller.MainDatabase;
import snapMain.controller.editor.CardEditorPaneController;
import snapMain.model.target.Card;
import snapMain.view.ViewSize;
import snapMain.view.fxml.FXMLMainGrabber;

public class CardEditorPane extends EditorPane {

    CardEditorPaneController controller;
    public CardEditorPane()
    {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("cardEditorPane.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(MainDatabase database, ViewSize v, Card c)
    {
        controller.initialize(database, v, c);
    }
}
