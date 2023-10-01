package snapMain.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import snapMain.model.database.CampaignDatabase;
import snapMain.model.thing.Faction;
import snapMain.model.thing.TargetType;
import snapMain.view.FactionPainter;
import snapMain.view.node.AgentSelectorGrid;
import snapMain.view.node.FactionSelectNode;
import snapMain.view.node.LocationMapNode;
import snapMain.view.node.PlanningDisplayNode;
import snapMain.view.pane.MainMenuPane;
import snapMain.view.pane.PlayerControlPane;

import static snapMain.model.constants.CampaignConstants.STRING_SEPARATOR;

public class PlayerControlPaneController extends ButtonToolBarPaneController {

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

    public void initialize(CampaignDatabase camp, MainDatabase cd, Faction f, Faction enemy, Faction unknown)
    {
        super.initialize(cd);
        campaignDatabase = camp;
        myFaction = f;
        myAgentSelector.initialize(mainDatabase, f, false);
        myLocationMap.initialize(mainDatabase, f, false);
        enemyIntel.initialize(cd, enemy, true);
        unknownIntel.initialize(cd, unknown, true);
        FactionPainter painter = new FactionPainter();
        playerControlPane.setStyle("-fx-faction-color: "+painter.getFactionColorString(f));

        //Initialize Sort Button
        for(String s: TargetType.CARD.getSortOptions())
        {
            MenuItem item = new MenuItem(s);
            item.setOnAction(e -> myAgentSelector.sortBy(s));
            item.setStyle("fx-text-fill:white");
            cardSortMenuButton.getItems().add(item);
        }
        //Initialize Filter Button
        for(String s: TargetType.CARD.getFilterOptions())
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
        mainMenuPane.initialize(mainDatabase);
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
        myAgentSelector.initialize(mainDatabase, myFaction, false);
        myLocationMap.initialize(mainDatabase, myFaction, false);
        passwordArea.clear();
    }
}
