package adventure.view.pane;

import adventure.controller.AdventureClearPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.view.pane.FullViewPane;

public class AdventureClearPane extends FullViewPane {

    AdventureClearPaneController controller;
    public AdventureClearPane()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("adventureClearPane.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase db, Adventure a)
    {
        controller.initialize(db, a);
    }
}
