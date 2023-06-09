package view.pane;

import controller.ControllerDatabase;
import controller.EditMenuController;
import view.fxml.FXMLGrabber;

public class EditorMenuPane extends CampaignPane {

    EditMenuController controller;
    public EditorMenuPane() {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("editorMenu.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase controllerDatabase) {
        controller.initialize(controllerDatabase);
    }
}
