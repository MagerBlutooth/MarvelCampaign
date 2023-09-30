package campaign.controller.node;

import campaign.controller.MainDatabase;
import campaign.controller.grid.BaseGridActionController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.StackPane;
import campaign.model.thing.Card;
import campaign.model.thing.Faction;
import campaign.model.thing.Location;
import campaign.model.thing.ThingType;
import campaign.view.FactionPainter;
import campaign.view.ViewSize;
import campaign.view.node.GridDisplayNode;

public class FactionDisplayNodeController {

    @FXML
    GridDisplayNode<Card> agentDisplay;

    @FXML
    GridDisplayNode<Location> locationDisplay;

    @FXML
    GridDisplayNode<Card> graveDisplay;

    @FXML
    Label factionLabel;
    @FXML
    StackPane copyAcceptanceStringPane;
    Faction faction;

    MainDatabase database;

    public void initialize(MainDatabase d, Faction f, boolean blind)
    {
        database = d;
        faction = f;
        factionLabel.setText(faction.getName());
        FactionPainter factionPainter = new FactionPainter();
        factionPainter.paintFactionText(factionLabel, f);

        BaseGridActionController<Card> agentController = new BaseGridActionController<>();
        agentController.initialize(d);
        BaseGridActionController<Location> locationController = new BaseGridActionController<>();
        locationController.initialize(d);
        BaseGridActionController<Card> graveController = new BaseGridActionController<>();
        graveController.initialize(d);

        agentDisplay.initialize(faction.getOwnedAgents(), ThingType.CARD, agentController, ViewSize.SMALL, blind);
        locationDisplay.initialize(faction.getOwnedLocations(), ThingType.LOCATION, locationController, ViewSize.SMALL, blind);
        graveDisplay.initialize(faction.getGrave(), ThingType.CARD, graveController, ViewSize.SMALL, blind);
    }

    public void copyStringToClipboard()
    {
        String nodeString = faction.toSaveString();
        ClipboardContent content = new ClipboardContent();
        content.putString(nodeString);
        Clipboard.getSystemClipboard().setContent(content);
        Label label = new Label("Copied String");
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
}
