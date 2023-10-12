package adventure.view.pane;

import adventure.controller.SectionViewPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.thing.Section;
import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.view.pane.FullViewPane;

public class SectionViewPane extends FullViewPane {

    SectionViewPaneController controller;
    public SectionViewPane()
    {
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("sectionViewPane.fxml", this);
        controller = fxmlAdventureGrabber.getController();
    }

    public void initialize(AdvMainDatabase dB, AdventureControlPane cP, Section s)
    {
        controller.initialize(dB, cP, s);
    }
}
