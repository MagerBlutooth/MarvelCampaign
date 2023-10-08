package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.view.pane.AdventureControlPane;
import javafx.fxml.FXML;

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
        controlPane.generateCard();
        controlPane.refreshToMatch();
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
