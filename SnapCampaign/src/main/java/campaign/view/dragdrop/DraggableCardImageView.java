package campaign.view.dragdrop;

import campaign.view.ViewSize;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import campaign.model.thing.Card;
import campaign.model.thing.ThingType;
import campaign.view.node.control.DraggableMapControlNode;

//Class created for Stationed Agents. Could be repurposed elsewhere if made less specific.
public class DraggableCardImageView extends ImageView implements Draggable<Card> {

    DraggableMapControlNode draggableMapControlNode;
    public DraggableCardImageView(Image i, ViewSize v, DraggableMapControlNode n)
    {
        super(i);
        this.setFitHeight(v.getSizeVal());
        this.setFitWidth(v.getSizeVal());
        draggableMapControlNode = n;
    }

    @Override
    public void removeOnDrag(Card c) {
        draggableMapControlNode.removeStationedAgent(c);
    }

    @Override
    public ThingType getThingType() {
        return ThingType.CARD;
    }
}
