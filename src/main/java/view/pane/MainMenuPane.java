package view.pane;

import controller.ControllerDatabase;
import controller.MainMenuController;
import javafx.scene.layout.StackPane;
import view.fxml.FXMLGrabber;

public class MainMenuPane extends CampaignPane {

    MainMenuController controller;
    public MainMenuPane()
    {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("mainMenu.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase controllerDatabase) {
        controller.initialize(controllerDatabase);
    }
}
