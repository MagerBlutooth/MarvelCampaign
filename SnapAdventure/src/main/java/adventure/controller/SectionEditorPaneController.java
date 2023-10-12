package adventure.controller;

import adventure.controller.manager.AdvEditorPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.thing.AdvLocation;
import adventure.view.node.SectionEditorNode;
import adventure.view.pane.AdvLocationManagerPane;
import javafx.fxml.FXML;
import snapMain.model.target.TargetType;
import snapMain.view.grabber.ThingImageGrabber;

public class SectionEditorPaneController extends AdvEditorPaneController
{
    ThingImageGrabber imageGrabber;
    @FXML
    SectionEditorNode sectionEditorNode;

    public SectionEditorPaneController()
    {
        imageGrabber = new ThingImageGrabber(TargetType.LOCATION);
    }

    public void initialize(AdvMainDatabase database, AdvLocation s)
    {
        super.initialize(database);
        sectionEditorNode.initialize(database, s);
    }

    @FXML
    private void saveSection()
    {
        AdvLocation s = sectionEditorNode.generateSection();
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
