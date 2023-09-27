package campaign.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import campaign.model.database.CampaignDatabase;
import campaign.model.database.FactionLabel;
import campaign.model.thing.Campaign;
import campaign.model.thing.CardList;
import campaign.model.thing.Faction;
import campaign.model.thing.LocationList;
import campaign.view.node.FactionDisplayNode;
import campaign.view.pane.CampaignStartPane;
import campaign.view.pane.MainMenuPane;

import java.util.Collections;

import static campaign.model.constants.CampaignConstants.*;

public class BaseStartPaneController extends ButtonToolBarPaneController {

    @FXML
    CampaignStartPane startPane;
    @FXML
    FactionDisplayNode shieldDisplay;
    @FXML
    FactionDisplayNode hydraDisplay;

    Campaign campaign;

    @Override
    public void initializeButtonToolBar() {
        MainMenuPane mainMenuPane = new MainMenuPane();
        mainMenuPane.initialize(controllerDatabase);
        buttonToolBar.initialize(mainMenuPane);
    }

    public void initialize(ControllerDatabase database)
    {
        super.initialize(database);
        CardList cards = new CardList(database.getEnabledCards());
        Collections.shuffle(cards.getCards());
        LocationList locations = new LocationList(database.getEnabledLocations());
        Collections.shuffle(locations.getLocations());
        CardList shieldAgents = new CardList(cards.subList(0, STARTING_CARDS));
        CardList hydraAgents = new CardList(cards.subList(STARTING_CARDS, STARTING_CARDS*2));
        for(int i = 0; i < STARTING_CAPTAINS; i++)
        {
            shieldAgents.get(i).setCaptain(true);
        }
        for(int i = 0; i < STARTING_CAPTAINS; i++)
        {
            hydraAgents.get(i).setCaptain(true);
        }
        LocationList shieldLocations = new LocationList(locations.subList(0, STARTING_LOCS));
        LocationList hydraLocations = new LocationList(locations.subList(STARTING_LOCS, STARTING_LOCS*2));
        shieldAgents.sort();
        hydraAgents.sort();
        shieldLocations.sort();
        hydraLocations.sort();

        CampaignDatabase campaignDatabase = new CampaignDatabase(controllerDatabase.getAdvMasterThingDatabase());
        Faction shield = new Faction(FactionLabel.SHIELD, shieldAgents, shieldLocations, campaignDatabase);
        Faction hydra = new Faction(FactionLabel.HYDRA, hydraAgents, hydraLocations, campaignDatabase);

        shieldDisplay.initialize(controllerDatabase, shield, false);
        hydraDisplay.initialize(controllerDatabase, hydra, false);

        campaign = new Campaign(campaignDatabase, shield, hydra);
    }

    @Override
    public Scene getCurrentScene() {
        return startPane.getScene();
    }
}