package snapMain.controller.node;

import snapMain.controller.MainDatabase;
import snapMain.controller.grid.BaseGridActionController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.StackPane;
import snapMain.model.target.Card;
import snapMain.model.target.Faction;
import snapMain.model.target.Location;
import snapMain.model.target.TargetType;
import snapMain.view.FactionPainter;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;

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

        agentDisplay.initialize(faction.getOwnedAgents(), TargetType.CARD, agentController, ViewSize.SMALL, blind);
        locationDisplay.initialize(faction.getOwnedLocations(), TargetType.LOCATION, locationController, ViewSize.SMALL, blind);
        graveDisplay.initialize(faction.getGrave(), TargetType.CARD, graveController, ViewSize.SMALL, blind);
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
