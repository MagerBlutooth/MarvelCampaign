package controller.grid;

import controller.ControllerDatabase;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import model.thing.Location;
import view.node.control.ControlNode;

public class MapGridActionController extends ThingActionController<Location> {


    @Override
    public void initialize(ControllerDatabase d, DraggableThingDisplayController<Location> c)
    {
        super.initialize(d, c);
    }

    @Override
    public void createContextMenu(ControlNode<Location> node)
    {
        ContextMenu contextMenu = new ContextMenu();
        Location location = node.getSubject();
        MenuItem ruinOption = new MenuItem();
        ruinOption.setOnAction(actionEvent ->
        {
            node.toggle();
            saveGridNode(node);
        });
        contextMenu.getItems().add(ruinOption);
        if(location.isRuined())
            ruinOption.setText("Repair");
        else
            ruinOption.setText("Ruin");
        node.setOnContextMenuRequested(e -> contextMenu.show(node, e.getScreenX(), e.getScreenY()));
    }

    @Override
    public void setMouseEvents(ControlNode<Location> displayControlNode) {

    }
}
