package adventure.controller;

import adventure.model.AdvControllerDatabase;
import adventure.model.Adventure;
import adventure.model.AdventureDatabase;
import adventure.view.AdvPane;
import adventure.view.node.ProfileNode;
import adventure.view.pane.AdvMainMenuPane;
import adventure.view.pane.AdvStartPane;
import campaign.controller.ButtonToolBarPaneController;
import campaign.model.thing.CardList;
import campaign.model.thing.LocationList;
import campaign.view.button.ButtonToolBar;
import javafx.fxml.FXML;
import javafx.scene.Scene;

import java.util.Collections;

import static adventure.model.AdventureConstants.STARTING_CAPTAINS;
import static adventure.model.AdventureConstants.STARTING_CARDS;

public class AdvStartPaneController extends AdvPaneController {

    public ButtonToolBar buttonToolBar;
    @FXML
    ProfileNode profile1;
    @FXML
    ProfileNode profile2;
    @FXML
    ProfileNode profile3;

    @Override
    public Scene getCurrentScene() {
        return null;
    }
}
