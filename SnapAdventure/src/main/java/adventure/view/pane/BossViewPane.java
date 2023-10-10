package adventure.view.pane;

import adventure.controller.BossViewPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.thing.Enemy;
import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.view.pane.FullViewPane;

public class BossViewPane extends FullViewPane {

    BossViewPaneController controller;
    public BossViewPane()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("bossViewPane.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase dB, AdventureControlPane cP, Enemy b)
    {
        controller.initialize(dB, cP, b);
    }
}
