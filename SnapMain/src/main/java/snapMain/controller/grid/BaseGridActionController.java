package snapMain.controller.grid;

import snapMain.controller.MainDatabase;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import snapMain.model.thing.BaseObject;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

public class BaseGridActionController<T extends BaseObject> implements GridActionController<T>{

    MainDatabase mainDatabase;

    public void initialize(MainDatabase database)
    {
        mainDatabase = database;
    }
    @Override
    public ControlNode<T> createControlNode(T t, IconImage i, ViewSize v, boolean blind) {
        ControlNode<T> node = new ControlNode<>();
        node.initialize(getDatabase(), t, i, v, blind);
        createTooltip(node);
        return node;
    }

    @Override
    public MainDatabase getDatabase() {
        return mainDatabase;
    }

    @Override
    public void saveGridNode(ControlNode<T> node) {

    }

    @Override
    public void createTooltip(ControlNode<T> n) {
        Tooltip tooltip = new Tooltip();
        ImageView tooltipImage = new ImageView(mainDatabase.grabImage(n.getSubject(), n.getThingType()));
        tooltipImage.setFitHeight(ViewSize.MEDIUM.getSizeVal());
        tooltipImage.setFitWidth(ViewSize.MEDIUM.getSizeVal());
        tooltip.setGraphic(tooltipImage);
        tooltip.setText(n.getSubject().getName());
        tooltip.setShowDelay(new Duration(1.0));
        Tooltip.install(n, tooltip);
    }

    @Override
    public void createContextMenu(ControlNode<T> n) {

    }

    @Override
    public void setMouseEvents(ControlNode<T> displayControlNode) {

    }
}
