package snapMain.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.LoadSelectPaneController;
import snapMain.view.fxml.FXMLMainGrabber;

public class LoadSelectPane extends FullViewPane {

    LoadSelectPaneController controller;
    public LoadSelectPane()
    {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("loadSelectPane.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(MainDatabase mainDatabase) {
        controller.initialize(mainDatabase);
    }
}
