package view.node;

import controller.grid.GridActionController;
import controller.grid.HallOfFameDisplayController;
import model.thing.HallOfFameEntry;
import model.thing.ThingList;
import model.thing.ThingType;
import view.ViewSize;

public class HallOfFameDisplayNode extends GridDisplayNode<HallOfFameEntry> {

    HallOfFameDisplayController controller;

    public HallOfFameDisplayNode() {
        fxmlGrabber.grabFXML("hallOfFameDisplayNode.fxml", this);
        controller = fxmlGrabber.getController();
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
