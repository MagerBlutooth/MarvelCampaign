package adventure.view.node;

import adventure.controller.TeamDisplayNodeController;
import adventure.model.Team;
import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.controller.ControllerDatabase;
import campaign.controller.node.FactionDisplayNodeController;
import campaign.model.thing.Faction;
import campaign.view.fxml.FXMLCampaignGrabber;
import javafx.scene.layout.StackPane;

public class TeamDisplayNode extends StackPane {

    TeamDisplayNodeController controller;

    public TeamDisplayNode() {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("teamDisplayNode.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    //Display Node should be "blind" if Captain status should be hidden
    public void initialize(ControllerDatabase d, Team t)
    {
        controller.initialize(d, t);
    }

}
