package records.view;

import snapMain.controller.MainDatabase;
import records.controller.TopDisplayNodeController;
import javafx.scene.layout.StackPane;
import snapMain.model.database.TargetDatabase;
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

    public void initialize(MainDatabase mainDatabase, TargetDatabase<HallOfFameEntry> hallOfFameEntries) {
        controller.initialize(mainDatabase, hallOfFameEntries);
    }
}
