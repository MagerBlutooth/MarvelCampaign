package snapMain.view.node;

import snapMain.controller.MainDatabase;
import snapMain.controller.grid.LocationMapNodeController;
import snapMain.controller.grid.WatcherLocationMapNodeController;
import snapMain.view.fxml.FXMLCampaignGrabber;
import javafx.scene.layout.StackPane;
import snapMain.model.thing.Faction;
import snapMain.model.thing.Location;

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

