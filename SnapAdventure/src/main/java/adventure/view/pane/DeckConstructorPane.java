package adventure.view.pane;

import adventure.controller.DeckConstructorPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.view.pane.FullViewPane;

public class DeckConstructorPane extends FullViewPane {

    DeckConstructorPaneController controller;

    public DeckConstructorPane()
    {
        FXMLAdventureGrabber adventureGrabber = new FXMLAdventureGrabber();
        adventureGrabber.grabFXML("deckConstructorPane.fxml", this);
        controller = adventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase cd, FullViewPane f, Adventure a)
    {
        controller.initialize(cd, f, a);
    }
}
