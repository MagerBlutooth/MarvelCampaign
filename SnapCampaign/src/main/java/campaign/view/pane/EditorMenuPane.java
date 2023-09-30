package campaign.view.pane;

import campaign.controller.MainDatabase;
import campaign.controller.EditMenuController;
import campaign.view.fxml.FXMLCampaignGrabber;

public class EditorMenuPane extends BasicPane {

    EditMenuController controller;
    public EditorMenuPane() {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("editorMenu.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase mainDatabase) {
        controller.initialize(mainDatabase);
    }
}
