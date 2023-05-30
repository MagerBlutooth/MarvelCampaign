package view.pane.editor;

import controller.ControllerDatabase;
import controller.editor.CardEditorPaneController;
import javafx.fxml.FXML;
import model.thing.Card;
import view.ViewSize;
import view.fxml.FXMLGrabber;

public class CardEditorPane extends EditorPane {

    CardEditorPaneController controller;
    public CardEditorPane()
    {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("cardEditorPane.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase database, ViewSize v, Card c)
    {
        controller.initialize(database, v, c);
    }
}
