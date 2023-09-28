package adventure.view.pane;

import adventure.controller.AdvStartPaneController;
import adventure.model.AdvControllerDatabase;
import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.view.fxml.FXMLCampaignGrabber;
import campaign.view.pane.BasicPane;

public class AdvStartPane extends BasicPane {

    AdvStartPaneController controller;

    public AdvStartPane()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("startPane.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvControllerDatabase database)
    {
        controller.initialize(database);
    }

}
