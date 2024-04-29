package records.view;

import records.controller.DeckCheckerPaneController;
import records.model.HallOfFameEntry;
import records.view.fxml.FXMLRecordGrabber;
import snapMain.controller.MainDatabase;
import snapMain.model.database.TargetDatabase;
import snapMain.view.pane.FullViewPane;

public class DeckCheckerPane extends FullViewPane {

    DeckCheckerPaneController controller;

    public DeckCheckerPane()
    {
        FXMLRecordGrabber grabber = new FXMLRecordGrabber();
        grabber.grabFXML("deckCheckerPane.fxml", this);
        controller = grabber.getController();
    }

    public void initialize(MainDatabase database, TargetDatabase<HallOfFameEntry> hallOfFameEntries)
    {
        controller.initialize(database, hallOfFameEntries);
    }
}
