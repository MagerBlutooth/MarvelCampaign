package snapMain.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.LoadSelectPaneController;
import snapMain.view.fxml.FXMLCampaignGrabber;

public class LoadSelectPane extends FullViewPane {

    LoadSelectPaneController controller;
    public LoadSelectPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("loadSelectPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase mainDatabase) {
        controller.initialize(mainDatabase);
    }
}
