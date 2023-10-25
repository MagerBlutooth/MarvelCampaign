package adventure.view.pane;

import adventure.controller.MainMenuController;
import adventure.model.AdvMainDatabase;
import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.view.pane.FullViewPane;

public class AdvMainMenuPane extends FullViewPane {

    MainMenuController controller;
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
