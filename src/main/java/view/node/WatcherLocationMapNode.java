package view.node;

import controller.ControllerDatabase;
import controller.grid.LocationMapNodeController;
import controller.grid.WatcherLocationMapNodeController;
import javafx.scene.layout.StackPane;
import model.thing.Faction;
import model.thing.Location;
import view.fxml.FXMLGrabber;

import java.util.List;

public class WatcherLocationMapNode extends StackPane {

    WatcherLocationMapNodeController controller;

    public WatcherLocationMapNode() {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("watcherLocationMapNode.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase cD, Faction faction, boolean blind) {
        controller.initialize(cD, faction, blind);
    }

    public LocationMapNodeController getController() {
        return controller;
    }

    public List<Location> getLocations() {
        return controller.getLocations();
    }

    public void reset() {
        controller.reset();
    }
}

