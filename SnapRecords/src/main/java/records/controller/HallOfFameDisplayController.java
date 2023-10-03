package records.controller;

import snapMain.controller.grid.GridActionController;
import snapMain.controller.grid.GridDisplayController;
import snapMain.model.logger.MLogger;
import snapMain.model.target.*;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;
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
    public void initialize(ThingList<HallOfFameEntry> entries, TargetType tType, GridActionController<HallOfFameEntry> controller, ViewSize v, boolean blind)
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
                HallOfFameEntry h = new HallOfFameEntry(mainDatabase.lookupDatabase(TargetType.CARD));
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
        IconImage i = mainDatabase.grabImage(t.getCaptain(), TargetType.CARD);
        ControlNode<HallOfFameEntry> n = getGridActionController().createControlNode(t, i, getViewSize(), isBlind());
        listOfObjects.add(n);
    }


}


