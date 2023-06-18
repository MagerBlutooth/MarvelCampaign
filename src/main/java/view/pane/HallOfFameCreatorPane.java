package view.pane;

import controller.ControllerDatabase;
import controller.grid.HallOfFameEntryCreatorPaneController;
import model.thing.HallOfFameEntry;
import view.fxml.FXMLGrabber;

import java.util.List;

public class HallOfFameCreatorPane extends CampaignPane {

    HallOfFameEntryCreatorPaneController controller;
    public HallOfFameCreatorPane()
    {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("hallOfFameCreator.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase controllerDatabase, HallOfFameEntry hof, List<HallOfFameEntry> otherEntries) {
        controller.initialize(controllerDatabase, hof, otherEntries);
    }
}
