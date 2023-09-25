package records.view;

import campaign.controller.ControllerDatabase;
import records.controller.TopDisplayNodeController;
import javafx.scene.layout.StackPane;
import campaign.model.database.ThingDatabase;
import campaign.view.fxml.FXMLCampaignGrabber;
import records.model.HallOfFameEntry;
import records.view.fxml.FXMLRecordGrabber;

public class TopDisplayNode extends StackPane {

    TopDisplayNodeController controller;
    public TopDisplayNode()
    {
        FXMLRecordGrabber fxmlRecordGrabber = new FXMLRecordGrabber();
        fxmlRecordGrabber.grabFXML("topDisplay.fxml", this);
        controller = fxmlRecordGrabber.getController();
    }

    public void initialize(ControllerDatabase controllerDatabase, ThingDatabase<HallOfFameEntry> hallOfFameEntries) {
        controller.initialize(controllerDatabase, hallOfFameEntries);
    }
}
