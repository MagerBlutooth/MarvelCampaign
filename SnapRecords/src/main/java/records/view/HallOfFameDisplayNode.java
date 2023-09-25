package records.view;

import campaign.controller.grid.GridActionController;
import campaign.model.thing.ThingList;
import campaign.model.thing.ThingType;
import campaign.view.ViewSize;
import campaign.view.node.GridDisplayNode;
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
    public void initialize(ThingList<HallOfFameEntry> things, ThingType tType, GridActionController<HallOfFameEntry> actionController, ViewSize viewSize, boolean blind)
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

    public ThingType getThingType()
    {
        return controller.getThingType();
    }
}
