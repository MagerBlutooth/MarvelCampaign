package adventure.view.node;

import adventure.view.fxml.FXMLAdventureGrabber;
import snapMain.controller.grid.GridActionController;
import snapMain.controller.grid.GridDisplayController;
import snapMain.controller.node.CampaignListNodeController;
import snapMain.model.thing.BaseObject;
import snapMain.model.thing.ThingList;
import snapMain.model.thing.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;


//Node object created for JavaFX nodes that have their own dedicated constructor, to have a built-in initialize method.
public abstract class AdvListNode<T extends BaseObject> extends StackPane {
    @FXML
    GridDisplayNode<T> list;

    protected FXMLAdventureGrabber fxmlAdventureGrabber;

    public AdvListNode() {
        fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML(getFXMLString(), this, this);
    }

    public GridDisplayController<T> getListNodeController()
    {
        return list.getController();
    }

    public void initialize(ThingList<T> things, TargetType t, GridActionController<T> c, ViewSize v, boolean blind) {
        list.initialize(things, t, c, v, blind);
    }

    public <V extends CampaignListNodeController<BaseObject>> V getController() {
        return fxmlAdventureGrabber.getController();
    }

    public String getFXMLString() {
        return "listNode.fxml";
    }

    public GridDisplayNode<T> getList()
    {
        return list;
    }
}
