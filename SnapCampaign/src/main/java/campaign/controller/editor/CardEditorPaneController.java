package campaign.controller.editor;

import campaign.controller.ControllerDatabase;
import javafx.fxml.FXML;
import campaign.model.thing.Card;
import campaign.model.thing.ThingType;
import campaign.view.ViewSize;
import campaign.view.grabber.ThingImageGrabber;
import campaign.view.node.editor.CardEditorNode;
import campaign.view.pane.manager.CardManagerPane;
import campaign.view.thing.CardView;

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
