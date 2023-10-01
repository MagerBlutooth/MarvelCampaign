package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.view.node.AdventureActionNode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Random;

public class AdventureActionNodeController {

    AdvMainDatabase mainDatabase;

    @FXML
    public void draftCard()
    {

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

    public void initialize(AdvMainDatabase database, Adventure a) {
        mainDatabase = database;
        adventure = a;

    }
}
