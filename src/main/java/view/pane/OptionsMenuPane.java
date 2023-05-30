package view.pane;

import controller.ControllerDatabase;
import controller.OptionsMenuPaneController;
import view.fxml.FXMLGrabber;

public class OptionsMenuPane extends CampaignPane {

    OptionsMenuPaneController controller;

    public OptionsMenuPane() {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("optionsMenu.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase controllerDatabase) {
        controller.initialize(controllerDatabase);
    }
}
