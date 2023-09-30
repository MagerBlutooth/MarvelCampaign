package records.view;

import campaign.controller.MainDatabase;
import campaign.view.pane.BasicPane;
import records.controller.HallOfFameEntryCreatorPaneController;
import records.model.HallOfFameEntry;
import records.view.fxml.FXMLRecordGrabber;

import java.util.List;

public class HallOfFameCreatorPane extends BasicPane {

    HallOfFameEntryCreatorPaneController controller;
    public HallOfFameCreatorPane()
    {
        FXMLRecordGrabber fxmlGrabber = new FXMLRecordGrabber();
        fxmlGrabber.grabFXML("hallOfFameCreator.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(MainDatabase mainDatabase, HallOfFameEntry hof, List<HallOfFameEntry> otherEntries) {
        controller.initialize(mainDatabase, hof, otherEntries);
    }
}
