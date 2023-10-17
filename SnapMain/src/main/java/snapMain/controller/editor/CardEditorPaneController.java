package snapMain.controller.editor;

import snapMain.controller.MainDatabase;
import javafx.fxml.FXML;
import snapMain.model.target.Card;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.grabber.TargetImageGrabber;
import snapMain.view.node.editor.CardEditorNode;
import snapMain.view.pane.manager.CardManagerPane;
import snapMain.view.thing.CardView;

public class CardEditorPaneController extends EditorPaneController {

    @FXML
    CardView imagePane;
    TargetImageGrabber imageGrabber;
    @FXML
    CardEditorNode cardEditorNode;

    public CardEditorPaneController()
    {
        imageGrabber = new TargetImageGrabber(TargetType.CARD);
    }

    public void initialize(MainDatabase database, ViewSize viewSize, Card card)
    {
        super.initialize(database);
        cardEditorNode.initialize(database, card);
        imagePane.initialize(mainDatabase, card, viewSize, true);
    }

    @FXML
    private void saveCard()
    {
        Card c = cardEditorNode.generateCard();
        mainDatabase.addCard(c, imagePane.getImage());
        imageGrabber.saveImage(imagePane.getImage(), c.getID());
        CardManagerPane cardManagerPane = new CardManagerPane();
        cardManagerPane.initialize(mainDatabase);
        changeScene(cardManagerPane);
    }

    @Override
    public void initializeButtonToolBar() {
        CardManagerPane cardManagerPane = new CardManagerPane();
        cardManagerPane.initialize(mainDatabase);
        buttonToolBar.initialize(cardManagerPane);
    }
}
