package view.node.control;

import controller.ControllerDatabase;
import controller.grid.LocationMapNodeController;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.constants.CampaignConstants;
import model.logger.MLogger;
import model.thing.*;
import view.IconImage;
import view.ViewSize;
import view.dragdrop.Draggable;
import view.dragdrop.DraggableCardImageView;

public class DraggableMapControlNode extends ControlNode<Location> implements Draggable<Card> {


    LocationMapNodeController mapController;
    MLogger logger = new MLogger(DraggableMapControlNode.class);
    Text nodeEffectText;
    HBox cardBox;
    Faction faction;
    public DraggableMapControlNode(LocationMapNodeController mController)
    {
        faction = mController.getFaction();
        mapController = mController;
        imageView = new ImageView();
        nodeEffectText = new Text();
        cardBox = new HBox();
        HBox nodeContents = new HBox();
        nodeContents.setAlignment(Pos.CENTER);
        cardBox.setAlignment(Pos.CENTER);
        cardBox.setSpacing(0.0);
        nodeContents.getChildren().addAll(imageView, nodeEffectText, cardBox);
        getChildren().add(nodeContents);
        this.setId("selectorGrid");
    }

    @Override
    public void initialize(ControllerDatabase db, Location l, IconImage i, ViewSize v, boolean blind)
    {
        super.initialize(db, l, i, v, blind);
        nodeEffectText.setText(l.getEffect());
        nodeEffectText.setId("nodeText");
        nodeEffectText.setWrappingWidth(400.0);
        drawCardBox();
    }

    public void drawCardBox() {
        cardBox.getChildren().clear();
        for(int i = 0; i < CampaignConstants.MAX_STATIONED_AGENTS; i++)
        {
            CardList cardList = getSubject().getStationedAgents();
            if(cardList.size()-1 >= i)
            {
                Card c = cardList.get(i);
                DraggableCardImageView cardView = new DraggableCardImageView(controllerDatabase.grabImage(c, ThingType.CARD), ViewSize.TINY, this);
                addDragDetected(cardView, c.getID());
                cardBox.getChildren().add(cardView);
            }
            else {
                ImageView blankView = new ImageView();
                blankView.setFitHeight(ViewSize.TINY.getSizeVal());
                blankView.setFitWidth(ViewSize.TINY.getSizeVal());
                cardBox.getChildren().add(blankView);
            }
        }
    }

    private void addDragDetected(ImageView cardView, int id) {
        cardView.setOnDragDetected(event -> {
            Dragboard dragboard = cardView.startDragAndDrop(TransferMode.MOVE);
            dragboard.setDragView(cardView.snapshot(null, null));
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString(id+"");
            dragboard.setContent(clipboardContent);
            logger.debug("Dragging Content: " + dragboard.getString());
            event.consume();
        });
    }
    public void removeStationedAgent(Card c) {
        faction.removeStationedAgent(c);
        drawCardBox();
    }

    @Override
    public void removeOnDrag(Card c) {
        removeStationedAgent(c);
    }
}
