package campaign.view.node;

import campaign.controller.MainDatabase;
import campaign.controller.grid.LocationMapNodeController;
import javafx.scene.layout.StackPane;
import campaign.model.thing.Faction;
import campaign.model.thing.Location;
import campaign.view.fxml.FXMLCampaignGrabber;

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

