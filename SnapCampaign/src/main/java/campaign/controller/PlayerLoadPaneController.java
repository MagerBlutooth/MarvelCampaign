package campaign.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import campaign.model.database.CampaignDatabase;
import campaign.model.database.FactionLabel;
import campaign.model.thing.CardList;
import campaign.model.thing.Faction;
import campaign.model.thing.LocationList;
import campaign.view.pane.PlayerControlPane;
import campaign.view.pane.PlayerLoadPane;
import campaign.view.pane.PlayerMainMenuPane;

import java.util.ArrayList;

public class PlayerLoadPaneController extends CampaignBasePaneController {

    @FXML
    PlayerLoadPane loadPane;
    @FXML
    TextArea passwordArea;

    public void initialize(ControllerDatabase database)
    {
        super.initialize(database);
    }

    @Override
    public Scene getCurrentScene() {
        return loadPane.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
        PlayerMainMenuPane mainMenuPane = new PlayerMainMenuPane();
        mainMenuPane.initialize(controllerDatabase);
        buttonToolBar.initialize(mainMenuPane);
    }

    @FXML
    public void loadFaction()
    {
        String password = passwordArea.getText().trim();
        CampaignDatabase campaignDatabase = new CampaignDatabase(controllerDatabase.getMasterThingDatabase());
        Faction faction = new Faction(password, campaignDatabase);
        PlayerControlPane playerControlPane = new PlayerControlPane();
        Faction enemyFaction = new Faction(FactionLabel.ENEMY, new CardList(new ArrayList<>()), new LocationList(new ArrayList<>()), campaignDatabase);
        Faction unknownFaction = new Faction(FactionLabel.UNKNOWN, faction.getUnownedAgents(), faction.getUnownedLocations(), campaignDatabase);
        playerControlPane.initialize(campaignDatabase, controllerDatabase, faction, enemyFaction, unknownFaction);
        changeScene(playerControlPane);
    }


}
