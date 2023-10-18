package adventure.controller.manager;

import adventure.model.AdvMainDatabase;
import adventure.model.target.AdvLocation;
import adventure.model.target.AdvLocationList;
import adventure.model.target.AdvToken;
import adventure.model.target.AdvTokenList;
import adventure.view.manager.AdvLocationManager;
import adventure.view.manager.AdvTokenManager;
import adventure.view.node.AdvLocationControlNode;
import adventure.view.node.AdvTokenControlNode;
import adventure.view.pane.AdvEditorMenuPane;
import adventure.view.pane.AdvTokenEditorPane;
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


public class AdvTokenManagerPaneController extends ManagerPaneController<AdvToken, AdvMainDatabase>
        implements GridActionController<AdvToken> {
    @FXML
    AdvTokenManager advTokenManager;

    @Override
    public void initializeButtonToolBar() {

        AdvEditorMenuPane menuPane = new AdvEditorMenuPane();
        menuPane.initialize(mainDatabase);
        buttonToolBar.initialize(menuPane);
    }

    @Override
    public Scene getCurrentScene() {
        return advTokenManager.getScene();
    }

    @Override
    public void initialize(AdvMainDatabase m) {
        super.initialize(m);
        AdvTokenList tokens = new AdvTokenList(m.getActualAdvTokens());
        advTokenManager.initialize(tokens, TargetType.ADV_TOKEN, this, ViewSize.MEDIUM, true);
    }

    @Override
    public void editSubject(ControlNode<AdvToken> node) {
        AdvTokenEditorPane tokenEditorPane = new AdvTokenEditorPane();
        tokenEditorPane.initialize(mainDatabase, node.getSubject());
        changeScene(tokenEditorPane);
    }

    @Override
    public ControlNode<AdvToken> createControlNode(AdvToken t, IconImage i, ViewSize v, boolean blind) {
        AdvTokenControlNode node = new AdvTokenControlNode();
        node.initialize(mainDatabase, t, i, v, blind);
        createContextMenu(node);
        setMouseEvents(node);
        return node;
    }

    @Override
    public void saveGridNode(ControlNode<AdvToken> node) {

    }

    @Override
    public void createTooltip(ControlNode<AdvToken> n) {

    }
    @Override
    public void createContextMenu(ControlNode<AdvToken> n) {
        ContextMenu rightClickMenu = new ContextMenu();
        MenuItem editMenuItem = new MenuItem("Edit");
        editMenuItem.setOnAction(actionEvent -> editSubject(n));
        rightClickMenu.getItems().add(editMenuItem);
        n.setOnContextMenuRequested(e -> rightClickMenu.show(n, e.getScreenX(), e.getScreenY()));
    }

    @Override
    public void setMouseEvents(ControlNode<AdvToken> controlNode) {
        AdvToken advToken = controlNode.getSubject();
        controlNode.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                mainDatabase.toggleAdvToken(advToken);
                mainDatabase.saveDatabase(TargetType.CARD);
                controlNode.toggle();
            }});

    }
}
