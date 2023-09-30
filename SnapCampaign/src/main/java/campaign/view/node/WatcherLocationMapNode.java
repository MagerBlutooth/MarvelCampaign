package campaign.view.node;

import campaign.controller.MainDatabase;
import campaign.controller.grid.LocationMapNodeController;
import campaign.controller.grid.WatcherLocationMapNodeController;
import campaign.view.fxml.FXMLCampaignGrabber;
import javafx.scene.layout.StackPane;
import campaign.model.thing.Faction;
import campaign.model.thing.Location;

import java.util.List;

public class WatcherLocationMapNode extends StackPane {

    WatcherLocationMapNodeController controller;

    public WatcherLocationMapNode() {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("watcherLocationMapNode.fxml", this);
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

    public int getInfluence() {
        return controller.getInfluence();
    }
}

