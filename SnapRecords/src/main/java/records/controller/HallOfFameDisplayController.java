package records.controller;

import campaign.controller.grid.GridActionController;
import campaign.controller.grid.GridDisplayController;
import campaign.model.logger.MLogger;
import campaign.model.thing.*;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.node.control.ControlNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import records.model.HallOfFameEntry;
import records.view.HallOfFameDisplayNode;

import java.util.ArrayList;
import java.util.List;

public class HallOfFameDisplayController extends GridDisplayController<HallOfFameEntry> {

    MLogger logger = new MLogger(HallOfFameDisplayController.class);
    @FXML
    HallOfFameDisplayNode hallOfFameDisplayNode;

    @Override
    public void initialize(ThingList<HallOfFameEntry> entries, ThingType tType, GridActionController<HallOfFameEntry> controller, ViewSize v, boolean blind)
    {
        super.initialize(entries, tType, controller, v, blind);
    }

    @Override
    protected void populateDisplay()
    {
        groupList.getChildren().clear();
        List<ControlNode<HallOfFameEntry>> listOfObjects = new ArrayList<>();
        thingList.sort();
        if(thingList.isEmpty())
        {
            HallOfFameEntry blankObject = null;
                HallOfFameEntry h = new HallOfFameEntry(controllerDatabase.lookupDatabase(ThingType.CARD));
                try {
                    blankObject = h.getClass().getDeclaredConstructor().newInstance();
                }
                catch(Exception ex) {
                    throw new RuntimeException(ex);
                }
            addNewNode(blankObject, listOfObjects);
        }

        for(HallOfFameEntry h: thingList)
        {
            addNewNode(h, listOfObjects);
        }
        ObservableList<ControlNode<HallOfFameEntry>> observableList = FXCollections.observableArrayList(listOfObjects);
        groupList.getChildren().addAll(observableList);
    }

    @Override
    protected void addNewNode(HallOfFameEntry t, List<ControlNode<HallOfFameEntry>> listOfObjects)
    {
        IconImage i = controllerDatabase.grabImage(t.getCaptain(), ThingType.CARD);
        ControlNode<HallOfFameEntry> n = getGridActionController().createControlNode(t, i, getViewSize(), isBlind());
        listOfObjects.add(n);
    }


}

