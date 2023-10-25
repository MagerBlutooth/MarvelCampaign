package snapMain.controller.node;

import snapMain.controller.MainDatabase;
import snapMain.controller.grid.BaseGridActionController;
import snapMain.controller.grid.FreeAgentGridActionController;
import snapMain.controller.grid.FreeLocationGridActionController;
import snapMain.controller.WatcherControlPaneController;
import snapMain.model.target.*;
import javafx.fxml.FXML;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;

public class FreeAgentSelectNodeController extends FactionSelectNodeController{

    @FXML
    GridDisplayNode<Card> freeAgentDisplay;

    @FXML
    GridDisplayNode<Location> freeLocationDisplay;

    @FXML
    GridDisplayNode<Card> freeGraveDisplay;

    WatcherControlPaneController watcherControlPaneController;

    public void initialize(MainDatabase d, WatcherControlPaneController watcherController, Faction free, String shieldName, String hydraName)
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

        freeAgentDisplay.initialize(this. faction.getOwnedAgents(), TargetType.CARD, agentController, ViewSize.SMALL, false);
        freeLocationDisplay.initialize(this. faction.getOwnedLocations(), TargetType.LOCATION, locationController, ViewSize.SMALL, false);
        freeGraveDisplay.initialize(this. faction.getGrave(), TargetType.CARD, graveController, ViewSize.SMALL, false);
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

    public void toShield(BaseObject t) {
        watcherControlPaneController.shieldHire(t);
    }

    public void toHydra(BaseObject t) {
        watcherControlPaneController.hydraHire(t);
    }

    public void toggleRuinLocation(Location l) {
        l.setRuined(!l.isRuined());
        refresh();
    }
}
