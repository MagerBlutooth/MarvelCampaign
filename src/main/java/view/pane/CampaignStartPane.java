package view.pane;

import controller.CampaignStartPaneController;
import controller.ControllerDatabase;
import javafx.scene.layout.StackPane;
import view.fxml.FXMLGrabber;

public class CampaignStartPane extends CampaignPane {

    CampaignStartPaneController controller;

    public CampaignStartPane()
    {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("campaignStartPane.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase database)
    {
        controller.initialize(database);
    }

}
