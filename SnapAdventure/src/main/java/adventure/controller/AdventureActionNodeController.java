package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.view.node.AdventureActionNode;
import adventure.view.pane.AdventureControlPane;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Random;

public class AdventureActionNodeController {

    AdvMainDatabase mainDatabase;
    AdventureControlPane controlPane;

    @FXML
    public void draftCard()
    {
        controlPane.draftCard();
        controlPane.refreshToMatch();
    }

    @FXML
    public void generateCard()
    {

    }

    @FXML
    public void searchCard()
    {

    }

    @FXML
    public void generateBoss()
    {

    }

    Adventure adventure;

    public void initialize(AdvMainDatabase database, Adventure a, AdventureControlPane cPane) {
        mainDatabase = database;
        adventure = a;
        controlPane = cPane;
    }
}
