package records.view;

import snapMain.controller.MainDatabase;
import snapMain.view.pane.manager.ManagerPane;
import records.controller.HallOfFameManagerController;
import records.view.fxml.FXMLRecordGrabber;

public class HallOfFameManagerPane extends ManagerPane {

    HallOfFameManagerController controller;
    public HallOfFameManagerPane()
    {
        FXMLRecordGrabber grabber = new FXMLRecordGrabber();
        grabber.grabFXML("hallOfFameManager.fxml", this);
        controller = grabber.getController();
    }

    public void initialize(MainDatabase database)
    {
        controller.initialize(database);
    }

}
