package view.pane.manager;

import controller.ControllerDatabase;
import controller.grid.CardManagerPaneController;
import view.fxml.FXMLGrabber;

public class CardManagerPane extends ManagerPane{

    CardManagerPaneController controller;
    public CardManagerPane()
    {

        FXMLGrabber grabber = new FXMLGrabber();
        grabber.grabFXML("cardManagerPane.fxml", this);
        controller = grabber.getController();
    }

    public void initialize(ControllerDatabase database)
    {
        controller.initialize(database);
    }

}
