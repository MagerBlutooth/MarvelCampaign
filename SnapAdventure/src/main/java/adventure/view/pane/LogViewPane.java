package adventure.view.pane;

import adventure.controller.LogViewPaneController;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.scene.control.TextArea;
import snapMain.view.pane.FullViewPane;

public class LogViewPane extends FullViewPane {

    LogViewPaneController controller;
    public LogViewPane() {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("adventureLogViewPane.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(TextArea text, FullViewPane backPane)
    {
        controller.initialize(text, backPane);
    }
}
