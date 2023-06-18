package controller.editor;

import controller.ControllerDatabase;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import model.thing.Card;
import model.thing.ThingType;
import view.ViewSize;
import view.grabber.ThingImageGrabber;
import view.node.editor.CardEditorNode;
import view.pane.manager.CardManagerPane;
import view.thing.CardView;

public class CardEditorPaneController extends EditorPaneController {

    @FXML
    CardView imagePane;
    ThingImageGrabber imageGrabber;
    @FXML
    CardEditorNode cardEditorNode;

    public CardEditorPaneController()
    {
        imageGrabber = new ThingImageGrabber(ThingType.CARD);
    }

    public void initialize(ControllerDatabase database, ViewSize viewSize, Card card)
    {
        super.initialize(database);
        cardEditorNode.initialize(database, card);
        imagePane.initialize(controllerDatabase, card, viewSize, true);
        imagePane.disableTooltip();
    }

    @FXML
    private void saveCard()
    {
        Card c = cardEditorNode.generateCard();
        controllerDatabase.addCard(c, imagePane.getImage());
        imageGrabber.saveImage(imagePane.getImage(), c.getID());
        CardManagerPane cardManagerPane = new CardManagerPane();
        cardManagerPane.initialize(controllerDatabase);
        changeScene(cardManagerPane);
    }

    @Override
    public void initializeButtonToolBar() {
        CardManagerPane cardManagerPane = new CardManagerPane();
        cardManagerPane.initialize(controllerDatabase);
        buttonToolBar.initialize(cardManagerPane);
    }
}
