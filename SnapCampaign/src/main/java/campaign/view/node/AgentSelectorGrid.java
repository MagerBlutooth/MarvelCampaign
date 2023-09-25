package campaign.view.node;

import campaign.controller.ControllerDatabase;
import campaign.controller.grid.AgentSelectorGridController;
import campaign.model.thing.Card;
import campaign.model.thing.Faction;
import campaign.view.fxml.FXMLCampaignGrabber;
import campaign.view.pane.CampaignPane;

import java.util.List;

public class AgentSelectorGrid extends CampaignPane {

    AgentSelectorGridController controller;

    public AgentSelectorGrid() {
        FXMLCampaignGrabber grabber = new FXMLCampaignGrabber();
        grabber.grabFXML("agentSelectorGrid.fxml", this);
        controller = grabber.getController();
    }

    public AgentSelectorGridController getController() {
        return controller;
    }

    public void initialize(ControllerDatabase cD, Faction faction, boolean blind) {
        controller.initialize(cD, faction, blind);
    }

    public void sortBy(String s) {
        controller.sortBy(s);
    }

    public void filterBy(String s, boolean notSelected) {
        controller.filterBy(s, notSelected);
    }

    public List<? super Card> getAgents() {
        return controller.getAgents();
    }

    public void reset() {
        controller.reset();
    }
}

