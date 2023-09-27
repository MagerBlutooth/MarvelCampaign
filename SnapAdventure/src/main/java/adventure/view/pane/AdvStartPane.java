package adventure.view.pane;

import adventure.controller.AdvStartPaneController;
import adventure.model.AdvControllerDatabase;
import campaign.view.fxml.FXMLCampaignGrabber;
import campaign.view.pane.BasicPane;

public class AdvStartPane extends BasicPane {

    AdvStartPaneController controller;

    public AdvStartPane()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("startPane.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(AdvControllerDatabase database)
    {
        controller.initialize(database);
    }

}
