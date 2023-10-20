package adventure.controller;

import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import adventure.view.node.ActiveCardControlNode;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

public class DeckGridController implements GridActionController<ActiveCard> {

    MainDatabase mainDatabase;
    ActiveCardList chosenCards;
    GridDisplayNode<ActiveCard> deckDisplay;
    IntegerProperty deckSizeProperty;

    DeckConstructorPaneController deckConstructorController;

    public void initialize(MainDatabase db, GridDisplayNode<ActiveCard> dDisplay, ActiveCardList recentDeck,
                           DeckConstructorPaneController cController)
    {
        mainDatabase = db;
        chosenCards = recentDeck;
        deckDisplay = dDisplay;
        deckDisplay.setPrefColumns(6);
        deckDisplay.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        deckConstructorController = cController;
        deckSizeProperty = new SimpleIntegerProperty();
        deckSizeProperty.set(chosenCards.size());
    }

    @Override
    public ControlNode<ActiveCard> createControlNode(ActiveCard card, IconImage i, ViewSize v, boolean blind) {
        ActiveCardControlNode node = new ActiveCardControlNode();
        node.initialize(mainDatabase, card, i, v, blind);
        setMouseEvents(node);
        return node;
    }

    @Override
    public ControlNode<ActiveCard> createEmptyNode(ViewSize v) {
        ControlNode<ActiveCard> cardNode = new ControlNode<>();
        cardNode.initialize(mainDatabase, new ActiveCard(), mainDatabase.grabBlankImage(TargetType.CARD),
                v,false);
        return cardNode;
    }

    @Override
    public MainDatabase getDatabase() {
        return mainDatabase;
    }

    @Override
    public void saveGridNode(ControlNode<ActiveCard> node) {

    }

    @Override
    public void createTooltip(ControlNode<ActiveCard> n) {

    }

    @Override
    public void createContextMenu(ControlNode<ActiveCard> n) {

    }

    @Override
    public void setMouseEvents(ControlNode<ActiveCard> controlNode) {
        controlNode.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                chosenCards.remove(controlNode.getSubject());
                deckConstructorController.toggleNodeLight(controlNode.getSubject());
                deckDisplay.refreshToMatch(chosenCards);
                setDeckSizeProperty();
            }
            e.consume();
        });
    }

    public boolean toggleEntry(ActiveCard card) {
        boolean toggled = false;
        if(chosenCards.contains(card)) {
            toggled = chosenCards.remove(card);
            setDeckSizeProperty();
        }
        else if(chosenCards.size() < SnapMainConstants.MAX_DECK_SIZE){
            toggled = chosenCards.add(card);
            setDeckSizeProperty();

        }
        deckDisplay.refreshToMatch(chosenCards);
        return toggled;
    }

    private void setDeckSizeProperty() {
        deckSizeProperty.set(chosenCards.size());
    }

    public ActiveCardList getDeck() {
        return chosenCards;
    }

    public void clear() {
        chosenCards.clear();
    }

    public IntegerProperty getDeckSizeProperty() {
        return deckSizeProperty;
    }

    public void toggleNodeLights() {
        for(ActiveCard c: chosenCards)
        {
            deckConstructorController.toggleNodeLight(c);
        }
    }

    public ActiveCardList getChosenCards() {
        return chosenCards;
    }

}
