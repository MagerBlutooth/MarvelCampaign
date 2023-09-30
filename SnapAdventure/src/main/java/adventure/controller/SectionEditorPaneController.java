package adventure.controller;

import adventure.controller.manager.AdvEditorPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.Section;
import adventure.view.node.SectionEditorNode;
import adventure.view.pane.AdvLocationManagerPane;
import campaign.model.thing.ThingType;
import campaign.view.grabber.ThingImageGrabber;
import javafx.fxml.FXML;

public class SectionEditorPaneController extends AdvEditorPaneController
{
    ThingImageGrabber imageGrabber;
    @FXML
    SectionEditorNode sectionEditorNode;

    public SectionEditorPaneController()
    {
        imageGrabber = new ThingImageGrabber(ThingType.LOCATION);
    }

    public void initialize(AdvMainDatabase database, Section s)
    {
        super.initialize(database);
        sectionEditorNode.initialize(database, s);
    }

    @FXML
    private void saveSection()
    {
        Section s = sectionEditorNode.generateSection();
        mainDatabase.modifySection(s);
        AdvLocationManagerPane locManagerPane = new AdvLocationManagerPane();
        locManagerPane.initialize(mainDatabase);
        changeScene(locManagerPane);
    }

    @Override
    public void initializeButtonToolBar() {
        AdvLocationManagerPane locManagerPane = new AdvLocationManagerPane();
        locManagerPane.initialize(mainDatabase);
        buttonToolBar.initialize(locManagerPane);
    }
}
