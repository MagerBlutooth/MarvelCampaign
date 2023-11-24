package snapMain.view.pane.manager;

import snapMain.controller.MainDatabase;
import snapMain.controller.grid.CardManagerPaneController;
import snapMain.model.target.CardList;
import snapMain.view.fxml.FXMLMainGrabber;

public class CardManagerPane extends ManagerPane {

    CardManagerPaneController controller;
    public CardManagerPane()
    {

        FXMLMainGrabber grabber = new FXMLMainGrabber();
        grabber.grabFXML("cardManagerPane.fxml", this);
        controller = grabber.getController();
    }

    public void initialize(MainDatabase database)
    {
        controller.initialize(database);
    }
}
