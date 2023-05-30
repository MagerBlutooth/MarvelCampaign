package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import model.database.CampaignDatabase;
import model.thing.Campaign;
import model.thing.Faction;
import view.FactionPainter;
import view.node.FactionSelectNode;
import view.pane.WatcherControlPane;
import view.pane.MainMenuPane;

public class CampaignPrepPaneController extends CampaignBasePaneController {

    @FXML
    VBox mainBox;

    @FXML
    FactionSelectNode shieldDisplay;
    @FXML
    FactionSelectNode hydraDisplay;

    Faction shield;
    Faction hydra;

    public void initialize(ControllerDatabase database, Faction s, Faction h)
    {
        super.initialize(database);
        shield = s;
        hydra = h;
        shieldDisplay.initialize(database, s, false);
        hydraDisplay.initialize(database, h, false);
    }

    @Override
    public void initializeButtonToolBar()
    {
        MainMenuPane mainMenuPane = new MainMenuPane();
        mainMenuPane.initialize(controllerDatabase);
        buttonToolBar.initialize(mainMenuPane);
    }

    public void addNode(Node n)
    {
        mainBox.getChildren().add(n);
    }

    @FXML
    public void startCampaign()
    {
        WatcherControlPane watcherControlPane = new WatcherControlPane();
        CampaignDatabase campaignDatabase = new CampaignDatabase(controllerDatabase.getMasterThingDatabase());
        watcherControlPane.initialize(controllerDatabase, new Campaign(campaignDatabase, shield, hydra));
        changeScene(watcherControlPane);
    }

    @Override
    public Scene getCurrentScene() {
        return mainBox.getScene();
    }
}
