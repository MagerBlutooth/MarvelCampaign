package snapMain.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.MainMenuController;
import snapMain.view.fxml.FXMLMainGrabber;

public class MainMenuPane extends FullViewPane {

    MainMenuController controller;
    public MainMenuPane()
    {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("mainMenu.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(MainDatabase mainDatabase) {
        controller.initialize(mainDatabase);
    }
}
