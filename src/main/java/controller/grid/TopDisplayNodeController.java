package controller.grid;

import controller.ControllerDatabase;
import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;
import model.thing.Card;
import model.thing.HallOfFameEntry;
import model.thing.ThingType;
import view.IconImage;
import view.node.TopDisplayEntry;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TopDisplayNodeController {

    private static final int TOP_COUNT = 10;
    ControllerDatabase controllerDatabase;
    @FXML
    public TilePane topList;

    Map<Card, Integer> cardUseMap = new ConcurrentHashMap<>();

    public void initialize(ControllerDatabase database, List<HallOfFameEntry> entries)
    {
        controllerDatabase = database;
        List<Card> cardList = database.getCards();
        for(Card c: cardList)
        {
            int count = 0;
            for(HallOfFameEntry e: entries)
            {
                if(e.contains(c))
                    count++;
            }
            cardUseMap.put(c, count);
        }

        int count = 1;
        Map<Card, Integer> topTen =
                cardUseMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(10)
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        for(Map.Entry<Card, Integer> e: topTen.entrySet())
        {
            TopDisplayEntry entry = createTopDisplayEntry(count, e.getKey(), e.getValue());
            topList.getChildren().add(entry);
            count++;
        }
        topList.setPrefColumns(3);
    }

    private TopDisplayEntry createTopDisplayEntry(int rank, Card card, int count) {
        TopDisplayEntry entry = new TopDisplayEntry();
        IconImage cardImage = controllerDatabase.grabImage(card, ThingType.CARD);
        entry.initialize(rank,cardImage, count);
        return entry;
    }
}
