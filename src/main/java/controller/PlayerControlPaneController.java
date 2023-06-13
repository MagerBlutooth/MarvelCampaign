package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.database.CampaignDatabase;
import model.thing.*;
import view.FactionPainter;
import view.node.AgentSelectorGrid;
import view.node.FactionSelectNode;
import view.node.LocationMapNode;
import view.node.PlanningDisplayNode;
import view.pane.MainMenuPane;
import view.pane.PlayerControlPane;

import static model.constants.CampaignConstants.STRING_SEPARATOR;

public class PlayerControlPaneController extends CampaignBasePaneController {

    @FXML
    PlayerControlPane playerControlPane;
    @FXML
    AgentSelectorGrid myAgentSelector;

    @FXML
    LocationMapNode myLocationMap;

    @FXML
    MenuButton cardSortMenuButton;
    @FXML
    MenuButton cardFilterMenuButton;

    @FXML
    StackPane copyAcceptanceStringPane;
    @FXML
    FactionSelectNode unknownIntel;
    @FXML
    FactionSelectNode enemyIntel;
    @FXML
    PlanningDisplayNode planningDisplayNode;
    @FXML
    TextArea passwordArea;

    CampaignDatabase campaignDatabase;
    Faction myFaction;

    public void initialize(CampaignDatabase camp, ControllerDatabase cd, Faction f, Faction enemy, Faction unknown)
    {
        super.initialize(cd);
        campaignDatabase = camp;
        myFaction = f;
        myAgentSelector.initialize(controllerDatabase, f, false);
        myLocationMap.initialize(controllerDatabase, f, false);
        enemyIntel.initialize(cd, enemy, true);
        unknownIntel.initialize(cd, unknown, true);
        FactionPainter painter = new FactionPainter();
        playerControlPane.setStyle("-fx-faction-color: "+painter.getFactionColorString(f));

        //Initialize Sort Button
        for(String s: ThingType.CARD.getSortOptions())
        {
            MenuItem item = new MenuItem(s);
            item.setOnAction(e -> myAgentSelector.sortBy(s));
            item.setStyle("fx-text-fill:white");
            cardSortMenuButton.getItems().add(item);
        }
        //Initialize Filter Button
        for(String s: ThingType.CARD.getFilterOptions())
        {
            CheckMenuItem item = new CheckMenuItem(s);
            item.setOnAction(e -> myAgentSelector.filterBy(s, !item.isSelected()));
            cardFilterMenuButton.getItems().add(item);
        }
        planningDisplayNode.initialize(cd, f);
    }

    @Override
    public Scene getCurrentScene() {
        return myAgentSelector.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
        MainMenuPane mainMenuPane = new MainMenuPane();
        mainMenuPane.initialize(controllerDatabase);
        buttonToolBar.initialize(mainMenuPane);
    }

    @FXML
    public void deploy()
    {
        //Card medBayAgent = myAgentSelector.getHealingAgent();
        String name = myFaction.getName();
        String deployString = name.concat(STRING_SEPARATOR);
        String agentString = myFaction.getOwnedAgents().toSaveString();
        String stationedAgents = myFaction.getOwnedLocationsAndMedbay().toString();
        deployString = deployString.concat(agentString+STRING_SEPARATOR);
        deployString = deployString.concat(stationedAgents);
        ClipboardContent content = new ClipboardContent();
        content.putString(deployString);
        Clipboard.getSystemClipboard().setContent(content);
        Text text = new Text("Positions Copied to Clipboard. Send to Watcher.");
        text.setFill(Color.WHITE);
        copyAcceptanceStringPane.getChildren().add(text);
    }
    @FXML
    public void resetAgents()
    {
        myAgentSelector.reset();
        myLocationMap.reset();
    }

    public void resetFaction()
    {
        String factionString = passwordArea.getText().trim();
        myFaction = new Faction(factionString, campaignDatabase);
        myAgentSelector.initialize(controllerDatabase, myFaction, false);
        myLocationMap.initialize(controllerDatabase, myFaction, false);
        passwordArea.clear();
    }
}
