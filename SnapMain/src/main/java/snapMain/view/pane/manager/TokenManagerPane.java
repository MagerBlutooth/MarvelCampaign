package snapMain.view.pane.manager;

import snapMain.controller.MainDatabase;
import snapMain.controller.grid.TokenManagerPaneController;
import snapMain.view.fxml.FXMLCampaignGrabber;

public class TokenManagerPane extends ManagerPane {

    TokenManagerPaneController controller;
    public TokenManagerPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("tokenManagerPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase database)
    {
        controller.initialize(database);
    }
}
