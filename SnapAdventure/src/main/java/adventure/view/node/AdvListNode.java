package adventure.view.node;

import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.controller.grid.GridActionController;
import campaign.controller.grid.GridDisplayController;
import campaign.controller.node.CampaignListNodeController;
import campaign.model.thing.Thing;
import campaign.model.thing.ThingList;
import campaign.model.thing.ThingType;
import campaign.view.ViewSize;
import campaign.view.fxml.FXMLCampaignGrabber;
import campaign.view.node.GridDisplayNode;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;


//Node object created for JavaFX nodes that have their own dedicated constructor, to have a built-in initialize method.
public abstract class AdvListNode<T extends Thing> extends StackPane {
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

    public void initialize(ThingList<T> things, ThingType t, GridActionController<T> c, ViewSize v, boolean blind) {
        list.initialize(things, t, c, v, blind);
    }

    public <V extends CampaignListNodeController<Thing>> V getController() {
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
