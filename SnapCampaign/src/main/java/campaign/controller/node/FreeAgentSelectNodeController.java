package campaign.controller.node;

import campaign.controller.ControllerDatabase;
import campaign.controller.grid.BaseGridActionController;
import campaign.controller.grid.FreeAgentGridActionController;
import campaign.controller.grid.FreeLocationGridActionController;
import campaign.controller.WatcherControlPaneController;
import campaign.model.thing.*;
import javafx.fxml.FXML;
import campaign.view.ViewSize;
import campaign.view.node.GridDisplayNode;

public class FreeAgentSelectNodeController extends FactionSelectNodeController{

    @FXML
    GridDisplayNode<Card> freeAgentDisplay;

    @FXML
    GridDisplayNode<Location> freeLocationDisplay;

    @FXML
    GridDisplayNode<Card> freeGraveDisplay;

    WatcherControlPaneController watcherControlPaneController;

    public void initialize(ControllerDatabase d, WatcherControlPaneController watcherController, Faction free, String shieldName, String hydraName)
    {
        database = d;
        faction = free;
        watcherControlPaneController = watcherController;

        FreeAgentGridActionController agentController = new FreeAgentGridActionController();
        agentController.initialize(d, this, shieldName, hydraName);
        FreeLocationGridActionController locationController = new FreeLocationGridActionController();
        locationController.initialize(d, this, shieldName, hydraName, freeLocationDisplay);
        BaseGridActionController<Card> graveController = new BaseGridActionController<>();
        graveController.initialize(d);

        freeAgentDisplay.initialize(this. faction.getOwnedAgents(), ThingType.CARD, agentController, ViewSize.SMALL, false);
        freeLocationDisplay.initialize(this. faction.getOwnedLocations(), ThingType.LOCATION, locationController, ViewSize.SMALL, false);
        freeGraveDisplay.initialize(this. faction.getGrave(), ThingType.CARD, graveController, ViewSize.SMALL, false);
    }

    @Override
    public void refresh()
    {
        freeAgentDisplay.refreshToMatch(faction.getOwnedAgents());
        freeLocationDisplay.refreshToMatch(faction.getOwnedLocations());
        freeGraveDisplay.refreshToMatch(faction.getGrave());
    }

    @Override
    public void eliminateAgent(Card agent) {
        faction.eliminateAgent(agent);
        refresh();
    }

    @Override
    public void reviveAgent(Card agent)
    {
        faction.reviveAgent(agent);
        refresh();
    }

    public void toShield(Thing t) {
        watcherControlPaneController.shieldHire(t);
    }

    public void toHydra(Thing t) {
        watcherControlPaneController.hydraHire(t);
    }

    public void toggleRuinLocation(Location l) {
        l.setRuined(!l.isRuined());
        refresh();
    }

    public void toggleWoundAgent(Card a) {
        a.setWounded(!a.isWounded());
        refresh();
    }
}
