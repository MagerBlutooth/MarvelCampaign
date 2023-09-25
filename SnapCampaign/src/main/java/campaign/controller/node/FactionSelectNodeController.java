package campaign.controller.node;

import campaign.controller.ControllerDatabase;
import campaign.controller.grid.AgentGridActionController;
import campaign.controller.grid.GraveGridActionController;
import campaign.controller.grid.MapGridActionController;
import campaign.model.thing.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.StackPane;
import campaign.model.sortFilter.LocationSortMode;
import campaign.view.FactionPainter;
import campaign.view.ViewSize;
import campaign.view.node.DraggableThingDisplayNode;
import campaign.view.node.FactionSelectNode;

public class FactionSelectNodeController {

    @FXML
    FactionSelectNode factionSelectNode;
    @FXML
    DraggableThingDisplayNode<Card> agentDisplay;

    @FXML
    DraggableThingDisplayNode<Location> locationDisplay;

    @FXML
    DraggableThingDisplayNode<Card> graveDisplay;

    @FXML
    Label factionLabel;
    @FXML
    StackPane copyAcceptanceStringPane;
    Faction faction;

    ControllerDatabase database;

    public void initialize(ControllerDatabase d, Faction f, boolean blind)
    {
        database = d;
        faction = f;
        factionLabel.setText(faction.getName());
        FactionPainter factionPainter = new FactionPainter();
        factionPainter.paintFactionText(factionLabel, f);
        AgentGridActionController agentGridController = initializeAgentController();
        MapGridActionController mapGridController = initializeMapGridController();
        GraveGridActionController graveGridController = initializeGraveGridController();
        LocationList ownedLocations = faction.getOwnedLocations();
        ownedLocations.setSortMode(LocationSortMode.NAME);
        agentDisplay.initialize(faction.getOwnedAgents(), ThingType.CARD, agentGridController,ViewSize.SMALL, blind);
        locationDisplay.initialize(faction.getOwnedLocations(), ThingType.LOCATION, mapGridController, ViewSize.SMALL, blind);
        graveDisplay.initialize(faction.getGrave(), ThingType.CARD, graveGridController, ViewSize.SMALL, blind);
    }

    private GraveGridActionController initializeGraveGridController() {
        GraveGridActionController g = new GraveGridActionController();
        g.initialize(database, graveDisplay.getController(), this);
        return g;
    }

    private MapGridActionController initializeMapGridController() {
        MapGridActionController m = new MapGridActionController();
        m.initialize(database, locationDisplay.getController());
        return m;
    }

    private AgentGridActionController initializeAgentController() {
        AgentGridActionController a = new AgentGridActionController();
        a.initialize(database, agentDisplay.getController(), this);
        return a;
    }

    public void copyStringToClipboard()
    {
        String nodeString = faction.toSaveString();
        ClipboardContent content = new ClipboardContent();
        content.putString(nodeString);
        Clipboard.getSystemClipboard().setContent(content);
        copyAcceptanceStringPane.getChildren().clear();
        Label label = new Label("Copied Faction String to Clipboard");
        label.setId("info");
        copyAcceptanceStringPane.getChildren().add(label);
    }

    public void refresh()
    {
        agentDisplay.refreshToMatch(faction.getOwnedAgents());
        locationDisplay.refreshToMatch(faction.getOwnedLocations());
        graveDisplay.refreshToMatch(faction.getGrave());
    }

    public void eliminateAgent(Card agent) {
        faction.eliminateAgent(agent);
        refresh();
    }

    public void reviveAgent(Card agent)
    {
        faction.reviveAgent(agent);
        refresh();
    }

    public void copyAgents() {
        CardList cards = faction.getAllOwnedAgents();
        String saveString = cards.toCardListString();
        ClipboardContent content = new ClipboardContent();
        content.putString(saveString);
        Clipboard.getSystemClipboard().setContent(content);
        Label label = new Label("Copied Agent List to Clipboard");
        label.setId("info");
        copyAcceptanceStringPane.getChildren().clear();
        copyAcceptanceStringPane.getChildren().add(label);

    }

    public void copyLocations()
    {
        LocationList locations = faction.getOwnedLocations();
        String saveString = locations.toListString();
        ClipboardContent content = new ClipboardContent();
        content.putString(saveString);
        Clipboard.getSystemClipboard().setContent(content);
        Label label = new Label("Copied Location List to Clipboard");
        label.setId("info");
        copyAcceptanceStringPane.getChildren().clear();
        copyAcceptanceStringPane.getChildren().add(label);
    }

    public void copyGrave() {
        CardList cards = faction.getGrave();
        String saveString = cards.toCardListString();
        ClipboardContent content = new ClipboardContent();
        content.putString(saveString);
        Clipboard.getSystemClipboard().setContent(content);
        Label label = new Label("Copied Agents in Grave to Clipboard");
        label.setId("info");
        copyAcceptanceStringPane.getChildren().clear();
        copyAcceptanceStringPane.getChildren().add(label);
    }

    public void addAgent(Card newCard) {
        faction.addThing(newCard);
        refresh();
    }

    public void removeAgent(Card removedCard) {
        faction.removeThing(removedCard);
        refresh();
    }
}
