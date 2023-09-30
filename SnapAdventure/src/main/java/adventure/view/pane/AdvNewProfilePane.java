package adventure.view.pane;

import adventure.controller.AdvNewProfilePaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.AdventureDatabase;
import adventure.model.adventure.Adventure;
import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.view.pane.BasicPane;

public class AdvNewProfilePane extends BasicPane {

    AdvNewProfilePaneController controller;

    public AdvNewProfilePane()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("newProfilePane.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase db, Adventure a, AdvStartPane backPane)
    {
        controller.initialize(db, a, backPane);
    }

}
