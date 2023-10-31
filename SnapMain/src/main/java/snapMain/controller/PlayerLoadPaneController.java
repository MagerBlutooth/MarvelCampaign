package snapMain.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import snapMain.model.database.CampaignDatabase;
import snapMain.model.database.FactionLabel;
import snapMain.model.target.CardList;
import snapMain.model.target.Faction;
import snapMain.model.target.LocationList;
import snapMain.view.pane.PlayerControlPane;
import snapMain.view.pane.PlayerLoadPane;
import snapMain.view.pane.PlayerMainMenuPane;

import java.util.ArrayList;

public class PlayerLoadPaneController extends ButtonToolBarPaneController {

    @FXML
    PlayerLoadPane loadPane;
    @FXML
    TextArea passwordArea;

    public void initialize(MainDatabase database)
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
        mainMenuPane.initialize(mainDatabase);
        buttonToolBar.initialize(mainMenuPane);
    }

    @FXML
    public void loadFaction()
    {
        String password = passwordArea.getText().trim();
        CampaignDatabase campaignDatabase = new CampaignDatabase(mainDatabase.getAdvMasterThingDatabase());
        Faction faction = new Faction(password, campaignDatabase);
        PlayerControlPane playerControlPane = new PlayerControlPane();
        Faction enemyFaction = new Faction(FactionLabel.ENEMY, new CardList(new ArrayList<>()), new LocationList(new ArrayList<>()), campaignDatabase);
        Faction unknownFaction = new Faction(FactionLabel.UNKNOWN, faction.getUnownedAgents(), faction.getUnownedLocations(), campaignDatabase);
        playerControlPane.initialize(campaignDatabase, mainDatabase, faction, enemyFaction, unknownFaction);
        changeScene(playerControlPane);
    }


}
