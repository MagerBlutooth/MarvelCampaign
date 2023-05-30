package view.dragdrop;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.thing.Card;
import model.thing.ThingType;
import view.ViewSize;
import view.node.control.DraggableMapControlNode;

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
