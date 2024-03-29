package snapMain.controller.grid;

import snapMain.controller.ButtonToolBarPaneController;
import snapMain.controller.MainDatabase;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import snapMain.model.target.BaseObject;
import snapMain.model.target.SnapTarget;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;
import snapMain.view.pane.EditorMenuPane;

//Generic controller used for controlling a full window
public abstract class ManagerPaneController<T extends SnapTarget, C extends MainDatabase> extends ButtonToolBarPaneController<C> implements GridActionController<T> {

    @Override
    public void initialize(C database)
    {
        super.initialize(database);
    }

    @Override
    public void initializeButtonToolBar() {
        EditorMenuPane menuPane = new EditorMenuPane();
        menuPane.initialize(mainDatabase);
        buttonToolBar.initialize(menuPane);
    }

    @Override
    public void createContextMenu(ControlNode<T> n) {
        ContextMenu rightClickMenu = new ContextMenu();
        MenuItem editMenuItem = new MenuItem("Edit");
        editMenuItem.setOnAction(actionEvent -> editSubject(n));
        rightClickMenu.getItems().add(editMenuItem);
        n.setOnContextMenuRequested(e -> rightClickMenu.show(n, e.getScreenX(), e.getScreenY()));
    }

    @Override
    public void createTooltip(ControlNode<T> n)
    {
        Tooltip tooltip = new Tooltip(n.getSubject().getName());
        ImageView imageView = new ImageView(n.getImage());
        imageView.setFitHeight(ViewSize.MEDIUM.getSizeVal());
        imageView.setFitWidth(ViewSize.MEDIUM.getSizeVal());
        tooltip.setGraphic(imageView);
        tooltip.setShowDelay(new Duration(0.5));
        tooltip.setTextAlignment(TextAlignment.CENTER);
        Tooltip.install(n, tooltip);
    }

    @Override
    public void setMouseEvents(ControlNode<T> controlNode) {
        controlNode.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                editSubject(controlNode);
            }
            e.consume();
        });
    }

    @Override
    public ControlNode<T> createControlNode(T t, IconImage i, ViewSize v, boolean blind) {
        ControlNode<T> n = new ControlNode<>();
        n.initialize(mainDatabase, t, i, v, blind);
        setMouseEvents(n);
        return n;
    }

    public abstract void editSubject(ControlNode<T> node);

}
