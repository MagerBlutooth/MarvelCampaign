package view.manager;

import model.thing.Thing;
import view.node.CampaignListNode;
import view.node.control.ControlNode;

import java.util.List;

public abstract class Manager<T extends Thing> extends CampaignListNode<T> {

    List<T> things;

    public List<T> getThings()
    {
        return things;
    }

}
