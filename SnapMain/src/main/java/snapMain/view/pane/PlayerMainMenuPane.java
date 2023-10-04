package snapMain.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.PlayerMainMenuController;
import snapMain.view.fxml.FXMLMainGrabber;

public class PlayerMainMenuPane extends FullViewPane {

    PlayerMainMenuController controller;
    public PlayerMainMenuPane()
    {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("playerMainMenu.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(MainDatabase mainDatabase) {
        controller.initialize(mainDatabase);
    }
}
