package snapMain.view.pane.manager;

import snapMain.controller.MainDatabase;
import snapMain.controller.grid.CardManagerPaneController;
import snapMain.view.fxml.FXMLCampaignGrabber;

public class CardManagerPane extends ManagerPane {

    CardManagerPaneController controller;
    public CardManagerPane()
    {

        FXMLCampaignGrabber grabber = new FXMLCampaignGrabber();
        grabber.grabFXML("cardManagerPane.fxml", this);
        controller = grabber.getController();
    }

    public void initialize(MainDatabase database)
    {
        controller.initialize(database);
    }

}
