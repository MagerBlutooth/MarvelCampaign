package view.node;

import controller.grid.GridDisplayController;
import controller.grid.GridActionController;
import controller.node.CampaignListNodeController;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import model.thing.Thing;
import model.thing.ThingList;
import model.thing.ThingType;
import view.ViewSize;
import view.fxml.FXMLGrabber;


//Node object created for JavaFX nodes that have their own dedicated constructor, to have a built-in initialize method.
public abstract class CampaignListNode<T extends Thing> extends StackPane {
    @FXML
    GridDisplayNode<T> list;

    protected FXMLGrabber fxmlGrabber;

    public CampaignListNode() {
        fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML(getFXMLString(), this, this);
    }

    public GridDisplayController<T> getListNodeController()
    {
        return list.getController();
    }

    public void initialize(ThingList<T> things, ThingType t, GridActionController<T> c, ViewSize v, boolean blind) {
        list.initialize(things, t, c, v, blind);
    }

    public <V extends CampaignListNodeController<Thing>> V getController() {
        return fxmlGrabber.getController();
    }

    public String getFXMLString() {
        return "listNode.fxml";
    }

    public GridDisplayNode<T> getList()
    {
        return list;
    }
}
