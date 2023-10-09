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
    }

    @FXML
    public void generateCard()
    {
        controlPane.generateCard();

    }

    @FXML
    public void searchCard()
    {
        controlPane.searchCard();
    }

    @FXML
    public void createClone()
    {
        controlPane.createClone();
    }

    Adventure adventure;

    public void initialize(AdvMainDatabase database, Adventure a, AdventureControlPane cPane) {
        mainDatabase = database;
        adventure = a;
        controlPane = cPane;
    }
}
