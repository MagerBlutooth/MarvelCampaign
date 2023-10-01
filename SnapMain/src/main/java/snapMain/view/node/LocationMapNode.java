package snapMain.view.node;

import snapMain.controller.MainDatabase;
import snapMain.controller.grid.LocationMapNodeController;
import javafx.scene.layout.StackPane;
import snapMain.model.thing.Faction;
import snapMain.model.thing.Location;
import snapMain.view.fxml.FXMLCampaignGrabber;

import java.util.List;

public class LocationMapNode extends StackPane {

    LocationMapNodeController controller;

    public LocationMapNode() {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("locationMapNode.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(MainDatabase cD, Faction faction, boolean blind) {
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

