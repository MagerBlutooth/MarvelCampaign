package adventure.controller.manager;

import adventure.model.AdvControllerDatabase;
import adventure.model.Boss;
import adventure.model.Section;
import adventure.view.node.BossControlNode;
import adventure.view.node.SectionControlNode;
import adventure.view.pane.AdvEditorMenuPane;
import adventure.view.pane.BossEditorPane;
import adventure.view.pane.SectionEditorPane;
import campaign.controller.grid.GridActionController;
import campaign.controller.grid.ManagerPaneController;
import campaign.model.thing.*;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.manager.CardManager;
import campaign.view.manager.LocationManager;
import campaign.view.menu.CardFilterMenuButton;
import campaign.view.menu.CardSortMenuButton;
import campaign.view.node.control.ControlNode;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class AdvLocationManagerPaneController extends ManagerPaneController<Location, AdvControllerDatabase> implements GridActionController<Location> {
    @FXML
    LocationManager locationManager;

    @Override
    public void initializeButtonToolBar() {

        AdvEditorMenuPane menuPane = new AdvEditorMenuPane();
        menuPane.initialize(controllerDatabase);
        buttonToolBar.initialize(menuPane);
    }

    @Override
    public Scene getCurrentScene() {
        return locationManager.getScene();
    }

    @Override
    public void initialize(AdvControllerDatabase m) {
        super.initialize(m);
        LocationList locations = new LocationList(m.getLocations());
        locationManager.initialize(locations, ThingType.CARD, this, ViewSize.MEDIUM, false);
    }

    @Override
    public void editSubject(ControlNode<Location> node) {
        SectionEditorPane locationEditorPane = new SectionEditorPane();
        Section s = controllerDatabase.getSection(node.getSubject());
        locationEditorPane.initialize(controllerDatabase, s);
        changeScene(locationEditorPane);
    }

    @Override
    public ControlNode<Location> createControlNode(Location l, IconImage i, ViewSize v, boolean blind) {
        SectionControlNode node = new SectionControlNode();
        node.initialize(controllerDatabase, l, i, v, blind);
        createContextMenu(node);
        setMouseEvents(node);
        return node;
    }

    @Override
    public void saveGridNode(ControlNode<Location> node) {

    }

    @Override
    public void createTooltip(ControlNode<Location> n) {

    }
    @Override
    public void createContextMenu(ControlNode<Location> n) {
        ContextMenu rightClickMenu = new ContextMenu();
        MenuItem editMenuItem = new MenuItem("Edit");
        editMenuItem.setOnAction(actionEvent -> editSubject(n));
        rightClickMenu.getItems().add(editMenuItem);
        n.setOnContextMenuRequested(e -> rightClickMenu.show(n, e.getScreenX(), e.getScreenY()));
    }

    @Override
    public void setMouseEvents(ControlNode<Location> controlNode) {
        Location loc = controlNode.getSubject();
        controlNode.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                controllerDatabase.toggleSection(loc);
                controllerDatabase.saveDatabase(ThingType.CARD);
                controlNode.toggle();
            }});

    }
}
