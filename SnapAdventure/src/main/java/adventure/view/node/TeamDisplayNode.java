package adventure.view.node;

import adventure.controller.TeamDisplayNodeController;
import adventure.model.Team;
import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.controller.MainDatabase;
import javafx.scene.layout.StackPane;

public class TeamDisplayNode extends StackPane {

    TeamDisplayNodeController controller;

    public TeamDisplayNode() {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("teamDisplayNode.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    //Display Node should be "blind" if Captain status should be hidden
    public void initialize(MainDatabase d, Team t)
    {
        controller.initialize(d, t);
    }

}
