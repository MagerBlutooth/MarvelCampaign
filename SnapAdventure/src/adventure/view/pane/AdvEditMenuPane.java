package adventure.view.pane;

import adventure.controller.AdvEditMenuPaneController;
import adventure.model.AdvControllerDatabase;
import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.controller.ControllerDatabase;
import campaign.view.pane.BasicPane;

public class AdvEditMenuPane extends BasicPane {
    AdvEditMenuPaneController controller;
    public AdvEditMenuPane() {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("editorMenu.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvControllerDatabase controllerDatabase) {
            controller.initialize(controllerDatabase);
        }
}

