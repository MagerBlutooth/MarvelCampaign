package adventure.controller.manager;

import adventure.model.AdvControllerDatabase;
import adventure.model.Section;
import adventure.model.SectionList;
import adventure.view.manager.SectionManager;
import adventure.view.node.SectionControlNode;
import adventure.view.pane.AdvEditorMenuPane;
import adventure.view.pane.SectionEditorPane;
import campaign.controller.grid.GridActionController;
import campaign.controller.grid.ManagerPaneController;
import campaign.model.thing.*;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.node.control.ControlNode;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class AdvSectionManagerPaneController extends ManagerPaneController<Section, AdvControllerDatabase> implements GridActionController<Section> {
    @FXML
    SectionManager sectionManager;

    @Override
    public void initializeButtonToolBar() {

        AdvEditorMenuPane menuPane = new AdvEditorMenuPane();
        menuPane.initialize(controllerDatabase);
        buttonToolBar.initialize(menuPane);
    }

    @Override
    public Scene getCurrentScene() {
        return sectionManager.getScene();
    }

    @Override
    public void initialize(AdvControllerDatabase m) {
        super.initialize(m);
        SectionList sections = new SectionList(m.getSections());
        sectionManager.initialize(sections, ThingType.LOCATION, this, ViewSize.MEDIUM, true);
    }

    @Override
    public void editSubject(ControlNode<Section> node) {
        SectionEditorPane locationEditorPane = new SectionEditorPane();
        locationEditorPane.initialize(controllerDatabase, node.getSubject());
        changeScene(locationEditorPane);
    }

    @Override
    public ControlNode<Section> createControlNode(Section s, IconImage i, ViewSize v, boolean blind) {
        SectionControlNode node = new SectionControlNode();
        node.initialize(controllerDatabase, s, i, v, blind);
        createContextMenu(node);
        setMouseEvents(node);
        return node;
    }

    @Override
    public void saveGridNode(ControlNode<Section> node) {

    }

    @Override
    public void createTooltip(ControlNode<Section> n) {

    }
    @Override
    public void createContextMenu(ControlNode<Section> n) {
        ContextMenu rightClickMenu = new ContextMenu();
        MenuItem editMenuItem = new MenuItem("Edit");
        editMenuItem.setOnAction(actionEvent -> editSubject(n));
        rightClickMenu.getItems().add(editMenuItem);
        n.setOnContextMenuRequested(e -> rightClickMenu.show(n, e.getScreenX(), e.getScreenY()));
    }

    @Override
    public void setMouseEvents(ControlNode<Section> controlNode) {
        Section loc = controlNode.getSubject();
        controlNode.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                controllerDatabase.toggleSection(loc.getLocation());
                controllerDatabase.saveDatabase(ThingType.CARD);
                controlNode.toggle();
            }});

    }
}
