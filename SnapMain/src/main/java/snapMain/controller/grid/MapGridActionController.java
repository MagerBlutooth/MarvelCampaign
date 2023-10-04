package snapMain.controller.grid;

import snapMain.controller.MainDatabase;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import snapMain.model.target.CardList;
import snapMain.model.target.Location;
import snapMain.model.target.LocationList;
import snapMain.view.dialog.LocationSearchSelectDialog;
import snapMain.view.node.control.ControlNode;

import java.util.Optional;

public class MapGridActionController extends ThingActionController<Location> {


    @Override
    public void initialize(MainDatabase d, DraggableThingDisplayController<Location> c)
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
        if(location.isRuined())
            ruinOption.setText("Repair");
        else
            ruinOption.setText("Ruin");
        node.setOnContextMenuRequested(e -> contextMenu.show(node, e.getScreenX(), e.getScreenY()));
        MenuItem addOption = new MenuItem("Add Location");
        addOption.setOnAction(e -> {
            LocationSearchSelectDialog dialog = new LocationSearchSelectDialog();
            dialog.initialize(mainDatabase, new LocationList(mainDatabase.getLocations()));
            Optional<Location> newLoc = dialog.showAndWait();
            newLoc.ifPresent(value -> displayController.add(newLoc.get()));
        });
        MenuItem deleteOption = new MenuItem("Delete Location");
        deleteOption.setOnAction(actionEvent ->
            displayController.remove(node.getSubject()));
        contextMenu.getItems().add(ruinOption);
        contextMenu.getItems().add(addOption);
        contextMenu.getItems().add(deleteOption);

    }

    @Override
    public void setMouseEvents(ControlNode<Location> displayControlNode) {

    }
}
