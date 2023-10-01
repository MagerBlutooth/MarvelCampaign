package snapMain.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.EditMenuController;
import snapMain.view.fxml.FXMLCampaignGrabber;

public class EditorMenuPane extends FullViewPane {

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
