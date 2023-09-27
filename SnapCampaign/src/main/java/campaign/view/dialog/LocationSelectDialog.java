package campaign.view.dialog;

import campaign.controller.ControllerDatabase;
import campaign.controller.grid.DialogGridActionController;
import campaign.view.ViewSize;
import campaign.view.node.control.ControlNode;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import campaign.model.thing.EffectThing;
import campaign.model.thing.Location;
import campaign.model.thing.LocationList;
import campaign.model.thing.ThingType;

import java.util.ArrayList;
import java.util.List;

public class LocationSelectDialog extends SelectDialog<Location> {

    @Override
    public void initialize(ControllerDatabase cd)
    {
        super.initialize(cd);
        List<Location> allLocations = controllerDatabase.getLocations();

        searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
            LocationList locs = new LocationList(new ArrayList<>());
            for(Location l: allLocations)
            {
                String name = l.getName().toLowerCase();
                String searchString = searchBar.textProperty().get().toLowerCase();
                if(name.contains(searchString))
                    locs.add(l);
            }
            DialogGridActionController<Location> gridController = new DialogGridActionController<>();
            gridController.intialize(cd, this);
            choices.initialize(locs, ThingType.LOCATION, gridController, ViewSize.MEDIUM, false);
    });
        setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return selection;
            }
            return null;
        });
    }

    @Override
    public void setChoice(Location l) {
        displayPane.getChildren().clear();
        ControlNode<EffectThing> viewNode = new ControlNode<>();
        viewNode.setMaxWidth(300.0);
        viewNode.initialize(controllerDatabase, l,controllerDatabase.grabImage(l, l.getThingType()), ViewSize.LARGE, true);
        displayPane.getChildren().add(viewNode);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.getChildren().add(new ControlNode<>());
        selection = l;
    }
}