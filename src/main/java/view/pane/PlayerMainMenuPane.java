package view.pane;

import controller.ControllerDatabase;
import controller.PlayerMainMenuController;
import view.fxml.FXMLGrabber;

public class PlayerMainMenuPane extends CampaignPane {

    PlayerMainMenuController controller;
    public PlayerMainMenuPane()
    {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("playerMainMenu.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase controllerDatabase) {
        controller.initialize(controllerDatabase);
    }
}
