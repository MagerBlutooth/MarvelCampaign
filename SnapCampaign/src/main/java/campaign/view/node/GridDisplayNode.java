package campaign.view.node;

import campaign.controller.grid.GridActionController;
import campaign.controller.grid.GridDisplayController;
import campaign.view.ViewSize;
import campaign.view.fxml.FXMLCampaignGrabber;
import javafx.scene.control.ScrollPane;
import campaign.model.logger.MLogger;
import campaign.model.thing.Thing;
import campaign.model.thing.ThingList;
import campaign.model.thing.ThingType;

public class GridDisplayNode<T extends Thing> extends ScrollPane {

    MLogger logger = new MLogger(GridDisplayNode.class);
    private final GridDisplayController<T> gridDisplayController;
    private FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();

    public GridDisplayNode() {
        fxmlCampaignGrabber.grabFXML("gridDisplayNode.fxml", this);
        gridDisplayController = fxmlCampaignGrabber.getController();
    }

    public FXMLCampaignGrabber getFXMLGrabber()
    {
        return fxmlCampaignGrabber;
    }

    public void initialize(ThingList<T> list, ThingType t, GridActionController<T> controller, ViewSize v, boolean blind)
    {
        gridDisplayController.initialize(list, t, controller, v, blind);
    }

    public void sortBy(String c) {
        gridDisplayController.sort(c);
    }

    public GridDisplayController<T> getController() {
        return gridDisplayController;
    }

    public void addThing(T t)
    {
        gridDisplayController.addThing(t);
    }

    public void removeThing(T t)
    {
        gridDisplayController.removeThing(t);
    }

    public void refreshToMatch(ThingList<T> things) {
        gridDisplayController.refresh(things);
    }

    public void setPrefColumns(int i) {
        gridDisplayController.setPrefColumns(i);
    }
}
