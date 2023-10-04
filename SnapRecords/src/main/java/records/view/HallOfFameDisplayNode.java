package records.view;

import snapMain.controller.grid.GridActionController;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;
import records.controller.HallOfFameDisplayController;
import records.model.HallOfFameEntry;
import records.view.fxml.FXMLRecordGrabber;

public class HallOfFameDisplayNode extends GridDisplayNode<HallOfFameEntry> {

    HallOfFameDisplayController controller;

    public HallOfFameDisplayNode() {
        FXMLRecordGrabber grabber = new FXMLRecordGrabber();
        grabber.grabFXML("hallOfFameDisplayNode.fxml", this);
        controller = grabber.getController();
    }

    @Override
    public void initialize(TargetList<HallOfFameEntry> things, TargetType tType, GridActionController<HallOfFameEntry> actionController, ViewSize viewSize, boolean blind)
    {
        controller.initialize(things, tType, actionController, viewSize, blind);
    }

    public String toString()
    {
        return controller.toString();
    }

    public void filterBy(String text, boolean remove) {
        controller.filter(text, remove);
    }

    @Override
    public void sortBy(String c) {
        controller.sort(c);
    }

    public TargetType getThingType()
    {
        return controller.getThingType();
    }
}
