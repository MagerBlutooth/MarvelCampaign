package records.view;

import campaign.controller.ControllerDatabase;
import campaign.view.fxml.FXMLCampaignGrabber;
import campaign.view.pane.manager.ManagerPane;
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

    public void initialize(ControllerDatabase database)
    {
        controller.initialize(database);
    }

}
