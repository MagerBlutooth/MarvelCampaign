package view.pane;

import controller.ControllerDatabase;
import controller.LoadSelectPaneController;
import view.fxml.FXMLGrabber;

public class LoadSelectPane extends CampaignPane {

    LoadSelectPaneController controller;
    public LoadSelectPane()
    {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("loadSelectPane.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase controllerDatabase) {
        controller.initialize(controllerDatabase);
    }
}
