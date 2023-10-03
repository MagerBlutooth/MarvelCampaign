package snapMain.view.node;

import snapMain.controller.grid.GridActionController;
import snapMain.controller.grid.GridDisplayController;
import snapMain.controller.node.CampaignListNodeController;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import snapMain.model.target.BaseObject;
import snapMain.model.target.ThingList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.fxml.FXMLCampaignGrabber;


//Node object created for JavaFX nodes that have their own dedicated constructor, to have a built-in initialize method.
public abstract class ListNode<T extends BaseObject> extends StackPane {
    @FXML
    GridDisplayNode<T> list;

    protected FXMLCampaignGrabber fxmlCampaignGrabber;

    public ListNode() {
        fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML(getFXMLString(), this, this);
    }

    public GridDisplayController<T> getListNodeController()
    {
        return list.getController();
    }

    public void initialize(ThingList<T> things, TargetType t, GridActionController<T> c, ViewSize v, boolean blind) {
        list.initialize(things, t, c, v, blind);
    }

    public <V extends CampaignListNodeController<BaseObject>> V getController() {
        return fxmlCampaignGrabber.getController();
    }

    public String getFXMLString() {
        return "listNode.fxml";
    }

    public GridDisplayNode<T> getList()
    {
        return list;
    }
}
