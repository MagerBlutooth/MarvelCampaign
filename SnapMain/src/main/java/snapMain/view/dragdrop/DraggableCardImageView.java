package snapMain.view.dragdrop;

import snapMain.view.ViewSize;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import snapMain.model.target.Card;
import snapMain.model.target.TargetType;
import snapMain.view.node.control.DraggableMapControlNode;

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
    public TargetType getThingType() {
        return TargetType.CARD;
    }
}
