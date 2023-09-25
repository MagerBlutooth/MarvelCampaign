package campaign.view.pane;

import campaign.controller.ControllerDatabase;
import campaign.controller.EditMenuController;
import campaign.view.fxml.FXMLCampaignGrabber;

public class EditorMenuPane extends CampaignPane {

    EditMenuController controller;
    public EditorMenuPane() {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("editorMenu.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(ControllerDatabase controllerDatabase) {
        controller.initialize(controllerDatabase);
    }
}
