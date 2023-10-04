package snapMain.view.pane;

import snapMain.controller.BaseStartPaneController;
import snapMain.controller.MainDatabase;
import snapMain.view.fxml.FXMLMainGrabber;

public class CampaignStartPane extends FullViewPane {

    BaseStartPaneController controller;

    public CampaignStartPane()
    {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("campaignStartPane.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(MainDatabase database)
    {
        controller.initialize(database);
    }

}
