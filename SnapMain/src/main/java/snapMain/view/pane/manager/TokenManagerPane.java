package snapMain.view.pane.manager;

import snapMain.controller.MainDatabase;
import snapMain.controller.grid.TokenManagerPaneController;
import snapMain.view.fxml.FXMLMainGrabber;

public class TokenManagerPane extends ManagerPane {

    TokenManagerPaneController controller;
    public TokenManagerPane()
    {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("tokenManagerPane.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(MainDatabase database)
    {
        controller.initialize(database);
    }
}
