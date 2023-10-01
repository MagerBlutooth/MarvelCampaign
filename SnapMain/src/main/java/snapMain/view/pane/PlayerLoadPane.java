package snapMain.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.PlayerLoadPaneController;
import snapMain.view.fxml.FXMLCampaignGrabber;

public class PlayerLoadPane extends FullViewPane {
    PlayerLoadPaneController controller;

    public PlayerLoadPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("playerLoadPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase database)
    {
        controller.initialize(database);
    }

}
