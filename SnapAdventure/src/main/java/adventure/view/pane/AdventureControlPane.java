package adventure.view.pane;

import adventure.controller.AdventureControlPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.AdventureDatabase;
import adventure.model.adventure.Adventure;
import adventure.model.target.Section;
import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.view.pane.FullViewPane;
import snapMain.view.pane.editor.EditorPane;

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

    public void generateCards() {
        controller.generateCards();
    }

    public void searchCard() {
        controller.searchFreeAgent();
    }

    public AdventureDatabase getAdventureDatabase() {
        return controller.getAdventureDatabase();
    }

    public void changeScene(FullViewPane fullViewPane) {
        controller.changeScene(fullViewPane);
    }
}
