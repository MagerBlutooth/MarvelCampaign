package view.pane.manager;

import controller.ControllerDatabase;
import controller.grid.TokenManagerPaneController;
import view.fxml.FXMLGrabber;

public class TokenManagerPane extends ManagerPane {

    TokenManagerPaneController controller;
    public TokenManagerPane()
    {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("tokenManagerPane.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase database)
    {
        controller.initialize(database);
    }
}
