package snapMain.controller;

import snapMain.model.target.*;
import snapMain.view.node.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import snapMain.model.database.CampaignDatabase;
import snapMain.model.database.FactionLabel;
import snapMain.model.database.MasterThingDatabase;
import snapMain.view.pane.MainMenuPane;

import java.util.ArrayList;

import static snapMain.model.constants.SnapMainConstants.STRING_SEPARATOR;

public class WatcherControlPaneController extends ButtonToolBarPaneController {

    @FXML
    HBox mainBox;

    @FXML
    FactionSelectNode shieldDisplay;
    @FXML
    FactionSelectNode hydraDisplay;
    @FXML
    FreeAgentSelectNode freeAgentDisplay;
    @FXML
    RandomizerNode randomizerNode;
    @FXML
    TextArea shieldPassword;
    @FXML
    TextArea hydraPassword;
    @FXML
    AgentSelectorGrid shieldAgentGrid;
    @FXML
    WatcherLocationMapNode shieldLocationMap;
    @FXML
    AgentSelectorGrid hydraAgentGrid;
    @FXML
    WatcherLocationMapNode hydraLocationMap;
    Campaign campaign;
    Faction shield;
    Faction hydra;
    Faction freeAgents;

    public void initialize(MainDatabase cd, Campaign c)
    {
        super.initialize(cd);
        campaign = c;
        shield = campaign.getShield();
        hydra = campaign.getHydra();
        freeAgents = campaign.getFreeFaction();
        shieldDisplay.initialize(mainDatabase, shield, false);
        hydraDisplay.initialize(mainDatabase, hydra, false);
        freeAgentDisplay.initialize(mainDatabase, this, freeAgents, shield.getName(), hydra.getName());
        randomizerNode.initialize(cd, c);
        shieldPassword.setText("");
        hydraPassword.setText("");
    }

    @Override
    public Scene getCurrentScene() {
        return mainBox.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
        MainMenuPane mainMenuPane = new MainMenuPane();
        mainMenuPane.initialize(mainDatabase);
        buttonToolBar.initialize(mainMenuPane);
    }

    @FXML
    public void loadShieldView()
    {
        String deployString = shieldPassword.getText().trim();
        MasterThingDatabase masterThingDatabase = mainDatabase.getAdvMasterThingDatabase();
        CampaignDatabase campaignDatabase = new CampaignDatabase(masterThingDatabase);
        String[] splitDeploy = deployString.split(STRING_SEPARATOR);
        FactionLabel name = FactionLabel.valueOf(splitDeploy[0].toUpperCase());
        String agentString = splitDeploy[1];
        String stationedAgents = splitDeploy[2];
        CardList agents = new CardList(new ArrayList<>());
        agents.fromSaveString(agentString, masterThingDatabase.getCards());
        LocationList locationSetup = new LocationList(new ArrayList<>());
        locationSetup.fromString(stationedAgents, masterThingDatabase);
        Faction f = new Faction(name, agents, locationSetup, campaignDatabase);
        shieldAgentGrid.initialize(mainDatabase,f, false);
        shieldLocationMap.initialize(mainDatabase, f, false);
    }
    @FXML
    public void loadHydraView()
    {
        String deployString = hydraPassword.getText().trim();
        MasterThingDatabase masterThingDatabase = mainDatabase.getAdvMasterThingDatabase();
        CampaignDatabase campaignDatabase = new CampaignDatabase(masterThingDatabase);
        String[] splitDeploy = deployString.split(STRING_SEPARATOR);
        FactionLabel name = FactionLabel.valueOf(splitDeploy[0].toUpperCase());
        String agentString = splitDeploy[1];
        String stationedAgents = splitDeploy[2];
        CardList agents = new CardList(new ArrayList<>());
        agents.fromSaveString(agentString, masterThingDatabase.getCards());
        LocationList locationSetup = new LocationList(new ArrayList<>());
        locationSetup.fromString(stationedAgents, masterThingDatabase);
        Faction f = new Faction(name, agents, locationSetup, campaignDatabase);
        hydraAgentGrid.initialize(mainDatabase,f, false);
        hydraLocationMap.initialize(mainDatabase, f, false);
    }

    @FXML
    public void saveShieldInfo()
    {
        CardList cards = new CardList(new ArrayList<>());
        for(Location l: shieldLocationMap.getLocations())
        {
            for(Card c: l.getStationedAgents())
            {
                if(c.isEnabled())
                    cards.add(c);
            }
        }
        int influence = shieldLocationMap.getInfluence();
        PlanningInfo shieldInfo = new PlanningInfo(cards, influence);
        String saveString = shieldInfo.toSaveString();
        ClipboardContent content = new ClipboardContent();
        content.putString(saveString);
        Clipboard.getSystemClipboard().setContent(content);
    }

    @FXML
    public void saveHydraInfo()
    {
        CardList cards = new CardList(new ArrayList<>());
        for(Location l: hydraLocationMap.getLocations())
        {
            for(Card c: l.getStationedAgents())
            {
                if(c.isEnabled())
                    cards.add(c);
            }
        }
        int influence = hydraLocationMap.getInfluence();
        PlanningInfo hydraInfo = new PlanningInfo(cards, influence);
        String saveString = hydraInfo.toSaveString();
        ClipboardContent content = new ClipboardContent();
        content.putString(saveString);
        Clipboard.getSystemClipboard().setContent(content);
    }

    public void shieldHire(BaseObject t) {
        freeAgents.removeThing(t);
        shield.addThing(t);
        refresh();
    }

    public void hydraHire(BaseObject t) {
        freeAgents.removeThing(t);
        hydra.addThing(t);
        refresh();
    }

    public void refresh()
    {
        shieldDisplay.refresh();
        hydraDisplay.refresh();
        freeAgentDisplay.refresh();
    }
}
