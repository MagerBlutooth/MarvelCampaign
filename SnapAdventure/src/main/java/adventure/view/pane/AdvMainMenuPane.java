package adventure.view.pane;

import adventure.model.AdvMainDatabase;
import adventure.view.fxml.FXMLAdventureGrabber;
import adventure.controller.AdvMainMenuController;
import campaign.view.pane.BasicPane;

public class AdvMainMenuPane extends BasicPane {

    AdvMainMenuController controller;
    public AdvMainMenuPane()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("mainMenu.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase controllerDatabase) {
        controller.initialize(controllerDatabase);
    }
}
