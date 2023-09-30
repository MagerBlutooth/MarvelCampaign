package adventure.view.pane;

import adventure.controller.AdvEditMenuPaneController;
import adventure.model.AdvMainDatabase;
import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.view.pane.BasicPane;

public class AdvEditorMenuPane extends BasicPane {
    AdvEditMenuPaneController controller;
    public AdvEditorMenuPane() {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("editorMenu.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase controllerDatabase) {
            controller.initialize(controllerDatabase);
        }
}

