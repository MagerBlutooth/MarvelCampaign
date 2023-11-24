package records.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import records.model.HallOfFameEntry;
import records.view.CardRecordControlNode;
import records.view.GridRecordDisplayNode;
import records.view.HallOfFameManagerPane;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import snapMain.controller.grid.ManagerPaneController;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

import java.util.*;


public class CardRecordDisplayPaneController extends ManagerPaneController<Card, MainDatabase>
        implements GridActionController<Card> {

    @FXML
    GridRecordDisplayNode<Card> gridDisplayNode;

    LinkedHashMap<Card, Integer> cardRecordMap;

    @Override
    public void initializeButtonToolBar() {

        HallOfFameManagerPane menuPane = new HallOfFameManagerPane();
        menuPane.initialize(mainDatabase);
        buttonToolBar.initialize(menuPane);
    }

    @Override
    public Scene getCurrentScene() {
        return gridDisplayNode.getScene();
    }

    public void initialize(MainDatabase m, TargetDatabase<HallOfFameEntry> hallOfFameEntries) {
        mainDatabase = m;

        cardRecordMap = new LinkedHashMap<>();
        initializeRecordMap(hallOfFameEntries);
        CardList cards = new CardList(cardRecordMap.keySet().stream().toList());
        gridDisplayNode.initialize(cards, this, ViewSize.MEDIUM,
                false);
        initializeButtonToolBar();
    }

    private void initializeRecordMap(TargetDatabase<HallOfFameEntry> hallOfFameEntries) {
        for(Card c: mainDatabase.getCards())
        {
            cardRecordMap.put(c, 0);
        }

        List<HallOfFameEntry> entryList = new ArrayList<>(hallOfFameEntries);
        for(HallOfFameEntry h: entryList)
        {
            for(Card c: h.getCards())
            {
                cardRecordMap.replace(c, cardRecordMap.get(c)+1);
            }
        }
        sortMap();
    }

    private void sortMap() {
            List<Map.Entry<Card, Integer>> list = new ArrayList<>(cardRecordMap.entrySet());
            list.sort(Map.Entry.comparingByValue());
            Collections.reverse(list);

        LinkedHashMap<Card, Integer> result = new LinkedHashMap<>();
            for (Map.Entry<Card, Integer> entry : list) {
                result.put(entry.getKey(), entry.getValue());
            }
            cardRecordMap = result;
    }

    @Override
    public void editSubject(ControlNode<Card> node) {

    }

    @Override
    public ControlNode<Card> createControlNode(Card c, IconImage i, ViewSize v, boolean revealed) {
        CardRecordControlNode node = new CardRecordControlNode();
        node.initialize(mainDatabase, c, cardRecordMap.get(c));
        createContextMenu(node);
        setMouseEvents(node);
        return node;
    }

    @Override
    public ControlNode<Card> createEmptyNode(ViewSize v) {
        ControlNode<Card> cardNode = new ControlNode<>();
        cardNode.initialize(mainDatabase, new Card(), mainDatabase.grabBlankImage(TargetType.CARD),
                v,false);
        return cardNode;
    }

    @Override
    public void saveGridNode(ControlNode<Card> node) {

    }

    @Override
    public void createTooltip(ControlNode<Card> n) {

    }
    @Override
    public void createContextMenu(ControlNode<Card> n) {
    }

    @Override
    public void setMouseEvents(ControlNode<Card> controlNode) {
    }
}
