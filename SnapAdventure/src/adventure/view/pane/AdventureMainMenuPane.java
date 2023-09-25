package adventure.view.pane;

import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.controller.ControllerDatabase;
import adventure.controller.AdventureMainMenuController;
import adventure.view.JourneyPane;
import campaign.view.fxml.FXMLCampaignGrabber;

public class AdventureMainMenuPane extends JourneyPane {

    AdventureMainMenuController controller;
    public AdventureMainMenuPane()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("adventureMainMenu.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(ControllerDatabase controllerDatabase) {
        controller.initialize(controllerDatabase);
    }
}
