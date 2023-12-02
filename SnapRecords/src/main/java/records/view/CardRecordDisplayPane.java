package records.view;

import records.controller.CardRecordDisplayPaneController;
import records.model.HallOfFameEntry;
import records.view.fxml.FXMLRecordGrabber;
import snapMain.controller.MainDatabase;
import snapMain.model.database.TargetDatabase;
import snapMain.view.pane.manager.ManagerPane;

public class CardRecordDisplayPane extends ManagerPane {

    CardRecordDisplayPaneController controller;

    public CardRecordDisplayPane()
    {
        FXMLRecordGrabber grabber = new FXMLRecordGrabber();
        grabber.grabFXML("cardRecordDisplayPane.fxml", this);
        controller = grabber.getController();
    }

    public void initialize(MainDatabase database, TargetDatabase<HallOfFameEntry> hallOfFameEntries)
    {
        controller.initialize(database, hallOfFameEntries);
    }
}
