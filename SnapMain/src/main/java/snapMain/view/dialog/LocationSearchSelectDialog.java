package snapMain.view.dialog;

import snapMain.controller.MainDatabase;
import snapMain.controller.grid.DialogGridActionController;
import snapMain.model.target.*;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;

import java.util.ArrayList;

public class LocationSearchSelectDialog extends SearchSelectDialog<Location> {

    @Override
    public void initialize(MainDatabase cd, TargetList<Location> selectableLocs)
    {
        super.initialize(cd, selectableLocs);

        searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
            LocationList locs = new LocationList(new ArrayList<>());
            for(Location l: selectableLocs)
            {
                String name = l.getName().toLowerCase();
                String searchString = searchBar.textProperty().get().toLowerCase();
                if(name.contains(searchString))
                    locs.add(l);
            }
            DialogGridActionController<Location> gridController = new DialogGridActionController<>();
            gridController.initialize(cd, this);
            choiceNodes.initialize(locs, TargetType.LOCATION, gridController, ViewSize.MEDIUM, false);
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
        ControlNode<EffectBaseObject> viewNode = new ControlNode<>();
        viewNode.setMaxWidth(300.0);
        viewNode.initialize(mainDatabase, l, mainDatabase.grabImage(l), ViewSize.LARGE, true);
        displayPane.getChildren().add(viewNode);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.getChildren().add(new ControlNode<>());
        selection = l;
    }
}
