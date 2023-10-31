package adventure.view.pane;

import adventure.controller.NewProfileOptionsPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.view.pane.FullViewPane;

public class AdvNewProfileOptionsPane extends FullViewPane {

    NewProfileOptionsPaneController controller;

    public AdvNewProfileOptionsPane()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("newProfileOptionsPane.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase db, Adventure a, AdvStartPane backPane)
    {
        controller.initialize(db, a, backPane);
    }

}
