package adventure.controller.manager;

import adventure.model.AdvMainDatabase;
import adventure.model.target.ActiveCard;
import adventure.model.target.base.AdvLocation;
import adventure.model.target.base.AdvLocationList;
import adventure.view.manager.AdvLocationManager;
import adventure.view.node.AdvLocationControlNode;
import adventure.view.pane.AdvEditorMenuPane;
import adventure.view.pane.SectionEditorPane;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import snapMain.controller.grid.GridActionController;
import snapMain.controller.grid.ManagerPaneController;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;


public class AdvLocationManagerPaneController extends ManagerPaneController<AdvLocation, AdvMainDatabase> implements GridActionController<AdvLocation> {
    @FXML
    AdvLocationManager advLocationManager;

    @Override
    public void initializeButtonToolBar() {

        AdvEditorMenuPane menuPane = new AdvEditorMenuPane();
        menuPane.initialize(mainDatabase);
        buttonToolBar.initialize(menuPane);
    }

    @Override
    public Scene getCurrentScene() {
        return advLocationManager.getScene();
    }

    @Override
    public void initialize(AdvMainDatabase m) {
        super.initialize(m);
        AdvLocationList sections = new AdvLocationList(m.getActualAdvLocations());
        advLocationManager.initialize(sections, TargetType.LOCATION, this, ViewSize.MEDIUM, true);
    }

    @Override
    public void editSubject(ControlNode<AdvLocation> node) {
        SectionEditorPane locationEditorPane = new SectionEditorPane();
        locationEditorPane.initialize(mainDatabase, node.getSubject());
        changeScene(locationEditorPane);
    }

    @Override
    public ControlNode<AdvLocation> createControlNode(AdvLocation s, IconImage i, ViewSize v, boolean blind) {
        AdvLocationControlNode node = new AdvLocationControlNode();
        node.initialize(mainDatabase, s, i, v, blind);
        createContextMenu(node);
        setMouseEvents(node);
        return node;
    }

    @Override
    public ControlNode<AdvLocation> createEmptyNode(ViewSize v) {
        ControlNode<AdvLocation> locNode = new ControlNode<>();
        locNode.initialize(mainDatabase, new AdvLocation(), mainDatabase.grabBlankImage(TargetType.LOCATION),
                v,false);
        return locNode;
    }

    @Override
    public void saveGridNode(ControlNode<AdvLocation> node) {

    }

    @Override
    public void createTooltip(ControlNode<AdvLocation> n) {

    }
    @Override
    public void createContextMenu(ControlNode<AdvLocation> n) {
        ContextMenu rightClickMenu = new ContextMenu();
        MenuItem editMenuItem = new MenuItem("Edit");
        editMenuItem.setOnAction(actionEvent -> editSubject(n));
        rightClickMenu.getItems().add(editMenuItem);
        n.setOnContextMenuRequested(e -> rightClickMenu.show(n, e.getScreenX(), e.getScreenY()));
    }

    @Override
    public void setMouseEvents(ControlNode<AdvLocation> controlNode) {
        AdvLocation loc = controlNode.getSubject();
        controlNode.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                mainDatabase.toggleSection(loc.getLocation());
                mainDatabase.saveDatabase(TargetType.CARD);
                controlNode.toggle();
            }});

    }
}
