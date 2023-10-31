package snapMain.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.OptionsMenuPaneController;
import snapMain.view.fxml.FXMLMainGrabber;

public class OptionsMenuPane extends FullViewPane {

    OptionsMenuPaneController controller;

    public OptionsMenuPane() {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("optionsMenu.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(MainDatabase mainDatabase) {
        controller.initialize(mainDatabase);
    }
}
