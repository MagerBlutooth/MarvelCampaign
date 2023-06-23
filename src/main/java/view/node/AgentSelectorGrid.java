package view.node;

import controller.ControllerDatabase;
import controller.grid.AgentSelectorGridController;
import model.thing.Card;
import model.thing.Faction;
import view.fxml.FXMLGrabber;
import view.pane.CampaignPane;

import java.util.List;

public class AgentSelectorGrid extends CampaignPane {

    AgentSelectorGridController controller;

    public AgentSelectorGrid() {
        FXMLGrabber grabber = new FXMLGrabber();
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

