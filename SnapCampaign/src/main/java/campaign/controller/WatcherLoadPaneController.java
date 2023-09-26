package campaign.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import campaign.model.constants.CampaignConstants;
import campaign.model.database.CampaignDatabase;
import campaign.model.database.CampaignLoader;
import campaign.model.thing.Campaign;
import campaign.model.thing.Faction;
import campaign.view.pane.MainMenuPane;
import campaign.view.pane.WatcherControlPane;
import campaign.view.pane.WatcherLoadPane;

import java.io.File;
import java.util.List;

public class WatcherLoadPaneController extends ButtonToolBarPaneController {

    @FXML
    WatcherLoadPane loadPane;
    @FXML
    TextArea passwordArea1;
    @FXML
    TextArea passwordArea2;
    @FXML
    Button loadButton;
    @FXML
    Button loadRecentButton;

    public void initialize(ControllerDatabase database)
    {
        super.initialize(database);
        loadButton.disableProperty().bind(Bindings.isEmpty(passwordArea1.textProperty()).and(Bindings.isEmpty(passwordArea2.textProperty())));
        configureLoadRecentButton();
    }

    private void configureLoadRecentButton() {
        File f = new File(CampaignConstants.CAMPAIGN_FILE);
        if(f.exists())
            loadRecentButton.setDisable(false);
        else
            loadRecentButton.setDisable(true);
    }

    @Override
    public Scene getCurrentScene() {
        return loadPane.getScene();
    }

    @Override
    public void initializeButtonToolBar() {
        MainMenuPane mainMenuPane = new MainMenuPane();
        mainMenuPane.initialize(controllerDatabase);
        buttonToolBar.initialize(mainMenuPane);
    }

    public void loadCampaign(String p1, String p2)
    {
        CampaignDatabase cDatabase = new CampaignDatabase(controllerDatabase.getAdvMasterThingDatabase());
        Faction shield = new Faction(p1, cDatabase);
        Faction hydra = new Faction(p2, cDatabase);
        WatcherControlPane watcherControlPane = new WatcherControlPane();
        Campaign campaign = new Campaign(cDatabase, shield, hydra);
        watcherControlPane.initialize(controllerDatabase, campaign);
        changeScene(watcherControlPane);
    }

    @FXML
    public void loadFactions()
    {
        String password1 = passwordArea1.getText().trim();
        String password2 = passwordArea2.getText().trim();
        loadCampaign(password1, password2);

    }

    @FXML
    public void loadRecent()
    {
        CampaignLoader campaignLoader = new CampaignLoader();
        List<String> campaignStrings = campaignLoader.loadCampaign();
        loadCampaign(campaignStrings.get(0), campaignStrings.get(1));

    }


}
