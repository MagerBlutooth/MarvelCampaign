package controller.grid;

import javafx.fxml.FXML;
import model.logger.MLogger;
import model.thing.HallOfFameEntry;
import model.thing.ThingList;
import model.thing.ThingType;
import view.IconImage;
import view.ViewSize;
import view.node.HallOfFameDisplayNode;
import view.node.control.ControlNode;
import view.node.control.HallOfFameControlNode;

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
    void addNewNode(HallOfFameEntry e, List<ControlNode<HallOfFameEntry>> listOfObjects) {
        IconImage i = controllerDatabase.grabImage(e.getCaptain(), ThingType.CARD);
                HallOfFameControlNode n = (HallOfFameControlNode) gridActionController.createControlNode(e, i, viewSize, blind);

        listOfObjects.add(n);
    }
    public void remove(HallOfFameEntry e) {
        thingList.remove(e);
        populateDisplay();
    }

    public void add(HallOfFameEntry e) {
        if(!thingList.contains(e))
            thingList.add(e);
        populateDisplay();
    }

    public List<HallOfFameEntry> getThings() {
        return thingList.getThings();
    }
}


