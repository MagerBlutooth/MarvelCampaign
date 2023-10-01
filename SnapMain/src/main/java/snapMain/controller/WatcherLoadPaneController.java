package snapMain.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import snapMain.model.constants.CampaignConstants;
import snapMain.model.database.CampaignDatabase;
import snapMain.model.database.CampaignLoader;
import snapMain.model.thing.Campaign;
import snapMain.model.thing.Faction;
import snapMain.view.pane.MainMenuPane;
import snapMain.view.pane.WatcherControlPane;
import snapMain.view.pane.WatcherLoadPane;

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

    public void initialize(MainDatabase database)
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
        mainMenuPane.initialize(mainDatabase);
        buttonToolBar.initialize(mainMenuPane);
    }

    public void loadCampaign(String p1, String p2)
    {
        CampaignDatabase cDatabase = new CampaignDatabase(mainDatabase.getAdvMasterThingDatabase());
        Faction shield = new Faction(p1, cDatabase);
        Faction hydra = new Faction(p2, cDatabase);
        WatcherControlPane watcherControlPane = new WatcherControlPane();
        Campaign campaign = new Campaign(cDatabase, shield, hydra);
        watcherControlPane.initialize(mainDatabase, campaign);
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
