package adventure.view.node;

import adventure.controller.TeamDisplayNodeController;
import adventure.model.AdvMainDatabase;
import adventure.model.Team;
import adventure.view.fxml.FXMLAdventureGrabber;
import adventure.view.pane.AdventureControlPane;
import javafx.scene.layout.StackPane;

public class TeamDisplayNode extends StackPane {

    TeamDisplayNodeController controller;

    public TeamDisplayNode() {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("teamDisplayNode.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase d, Team t, AdventureControlPane a)
    {
        controller.initialize(d, t, a);
    }

    public void refresh() {
        controller.refresh();
    }
}
