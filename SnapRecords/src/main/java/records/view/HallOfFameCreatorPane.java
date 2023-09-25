package records.view;

import campaign.controller.ControllerDatabase;
import campaign.view.pane.CampaignPane;
import records.controller.HallOfFameEntryCreatorPaneController;
import records.model.HallOfFameEntry;
import records.view.fxml.FXMLRecordGrabber;

import java.util.List;

public class HallOfFameCreatorPane extends CampaignPane {

    HallOfFameEntryCreatorPaneController controller;
    public HallOfFameCreatorPane()
    {
        FXMLRecordGrabber fxmlGrabber = new FXMLRecordGrabber();
        fxmlGrabber.grabFXML("hallOfFameCreator.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase controllerDatabase, HallOfFameEntry hof, List<HallOfFameEntry> otherEntries) {
        controller.initialize(controllerDatabase, hof, otherEntries);
    }
}
