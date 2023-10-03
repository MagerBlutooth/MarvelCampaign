package snapMain.view.node;

import snapMain.controller.grid.GridActionController;
import snapMain.controller.grid.GridDisplayController;
import snapMain.view.ViewSize;
import snapMain.view.fxml.FXMLCampaignGrabber;
import javafx.scene.control.ScrollPane;
import snapMain.model.logger.MLogger;
import snapMain.model.target.BaseObject;
import snapMain.model.target.ThingList;
import snapMain.model.target.TargetType;

public class GridDisplayNode<T extends BaseObject> extends ScrollPane {

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

    public void initialize(ThingList<T> list, TargetType t, GridActionController<T> controller, ViewSize v, boolean blind)
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

    public void update(T subject) {
        gridDisplayController.update(subject);
    }
}
