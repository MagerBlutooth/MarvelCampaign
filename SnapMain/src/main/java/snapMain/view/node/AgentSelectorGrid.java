package snapMain.view.node;

import snapMain.controller.MainDatabase;
import snapMain.controller.grid.AgentSelectorGridController;
import snapMain.model.target.Card;
import snapMain.model.target.Faction;
import snapMain.view.fxml.FXMLMainGrabber;
import snapMain.view.pane.FullViewPane;

import java.util.List;

public class AgentSelectorGrid extends FullViewPane {

    AgentSelectorGridController controller;

    public AgentSelectorGrid() {
        FXMLMainGrabber grabber = new FXMLMainGrabber();
        grabber.grabFXML("agentSelectorGrid.fxml", this);
        controller = grabber.getController();
    }

    public AgentSelectorGridController getController() {
        return controller;
    }

    public void initialize(MainDatabase cD, Faction faction, boolean blind) {
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

