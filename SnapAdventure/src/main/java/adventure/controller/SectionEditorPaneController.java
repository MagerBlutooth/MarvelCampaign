package adventure.controller;

import adventure.controller.manager.AdvEditorPaneController;
import adventure.model.AdvControllerDatabase;
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

    public void initialize(AdvControllerDatabase database, Section s)
    {
        super.initialize(database);
        sectionEditorNode.initialize(database, s);
    }

    @FXML
    private void saveSection()
    {
        Section s = sectionEditorNode.generateSection();
        controllerDatabase.modifySection(s);
        AdvLocationManagerPane locManagerPane = new AdvLocationManagerPane();
        locManagerPane.initialize(controllerDatabase);
        changeScene(locManagerPane);
    }

    @Override
    public void initializeButtonToolBar() {
        AdvLocationManagerPane locManagerPane = new AdvLocationManagerPane();
        locManagerPane.initialize(controllerDatabase);
        buttonToolBar.initialize(locManagerPane);
    }
}
