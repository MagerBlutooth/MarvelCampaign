package view.pane.manager;

import controller.ControllerDatabase;
import controller.grid.CardManagerPaneController;
import controller.grid.HallOfFameManagerController;
import model.database.HallOfFameLoader;
import view.fxml.FXMLGrabber;

public class HallOfFameManagerPane extends ManagerPane{

    HallOfFameManagerController controller;
    public HallOfFameManagerPane()
    {

        FXMLGrabber grabber = new FXMLGrabber();
        grabber.grabFXML("hallOfFameManager.fxml", this);
        controller = grabber.getController();
    }

    public void initialize(ControllerDatabase database)
    {
        controller.initialize(database);
    }

}
