package view.node;

import controller.ControllerDatabase;
import controller.grid.LocationMapNodeController;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import model.thing.Faction;
import model.thing.Location;
import view.fxml.FXMLGrabber;

import java.util.List;

public class LocationMapNode extends StackPane {

    LocationMapNodeController controller;

    public LocationMapNode() {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("locationMapNode.fxml", this);
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

