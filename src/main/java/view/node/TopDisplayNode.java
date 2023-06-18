package view.node;

import controller.ControllerDatabase;
import controller.grid.TopDisplayNodeController;
import javafx.scene.layout.StackPane;
import model.database.ThingDatabase;
import model.thing.HallOfFameEntry;
import view.fxml.FXMLGrabber;

public class TopDisplayNode extends StackPane {

    TopDisplayNodeController controller;
    public TopDisplayNode()
    {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("topDisplay.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase controllerDatabase, ThingDatabase<HallOfFameEntry> hallOfFameEntries) {
        controller.initialize(controllerDatabase, hallOfFameEntries);
    }
}
