package adventure.view.pane;

import adventure.controller.AdvNewProfilePaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.view.pane.FullViewPane;

public class AdvNewProfilePane extends FullViewPane {

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
