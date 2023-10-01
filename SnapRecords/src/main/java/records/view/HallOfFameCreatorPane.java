package records.view;

import snapMain.controller.MainDatabase;
import snapMain.view.pane.FullViewPane;
import records.controller.HallOfFameEntryCreatorPaneController;
import records.model.HallOfFameEntry;
import records.view.fxml.FXMLRecordGrabber;

import java.util.List;

public class HallOfFameCreatorPane extends FullViewPane {

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
