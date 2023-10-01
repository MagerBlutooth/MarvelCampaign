package snapMain.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.OptionsMenuPaneController;
import snapMain.view.fxml.FXMLCampaignGrabber;

public class OptionsMenuPane extends FullViewPane {

    OptionsMenuPaneController controller;

    public OptionsMenuPane() {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("optionsMenu.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase mainDatabase) {
        controller.initialize(mainDatabase);
    }
}
