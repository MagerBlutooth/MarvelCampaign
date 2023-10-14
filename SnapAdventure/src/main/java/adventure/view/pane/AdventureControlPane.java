package adventure.view.pane;

import adventure.controller.AdventureControlPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.AdventureDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.stats.MatchResult;
import adventure.model.thing.Section;
import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.model.target.CardList;
import snapMain.view.pane.editor.EditorPane;

import java.util.Optional;

public class AdventureControlPane extends EditorPane {

    AdventureControlPaneController controller;

    public AdventureControlPane()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("adventureControlPane.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase db, Adventure a)
    {
        controller.initialize(db, a);
    }

    public Adventure getAdventure()
    {
        return controller.getAdventure();
    }

    public void skipSection(Section section) {
        controller.skipSection(section);
    }

    public void refreshToMatch() {
        controller.refreshToMatch();
    }

    public void completeCurrentSection() {
        controller.completeSection();
    }

    public void draftCard() {
        controller.draftCard();
    }

    public void generateCard() {
        controller.generateCard();
    }

    public void searchCard() {
        controller.searchFreeAgent();
    }

    public AdventureDatabase getAdventureDatabase() {
        return controller.getAdventureDatabase();
    }
}
