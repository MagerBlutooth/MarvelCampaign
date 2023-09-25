package records.controller;

import campaign.controller.ControllerDatabase;
import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;
import javafx.util.Pair;
import campaign.model.thing.Card;
import campaign.model.thing.ThingType;
import campaign.view.IconImage;
import records.model.HallOfFameEntry;
import records.view.TopDisplayEntry;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static campaign.model.constants.CampaignConstants.BASIC_COST_MAX;
import static campaign.model.constants.CampaignConstants.BASIC_COST_MIN;

public class TopDisplayNodeController {

    ControllerDatabase controllerDatabase;
    @FXML
    public TilePane topList;
    Map<Integer, Map<Card, Integer>> cardUseMap = new ConcurrentHashMap<>();

    public void initialize(ControllerDatabase database, List<HallOfFameEntry> entries) {
        controllerDatabase = database;
        List<Card> cardList = database.getCards();
        for (int i = BASIC_COST_MIN; i <= BASIC_COST_MAX; i++) {
            cardUseMap.put(i, new ConcurrentHashMap<>());
        }
        for (Card c : cardList) {
            int count = 0;
            for (HallOfFameEntry e : entries) {
                if (e.contains(c))
                    count++;
            }
            int cost = c.getCost();

            cost = Math.min(BASIC_COST_MAX, cost);
            cost = Math.max(BASIC_COST_MIN, cost);

            cardUseMap.get(cost).put(c, count);
        }

        for (int i = BASIC_COST_MIN; i <= BASIC_COST_MAX; i++) {
            Map<Card, Integer> cardUseMapForCost = cardUseMap.get(i);
            int CARD_LIMIT = 3;
            Map<Card, Integer> topCards =
                    cardUseMapForCost.entrySet().stream()
                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                            .limit(CARD_LIMIT)
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            List<Map.Entry<Card, Integer>> chosenCards = new ArrayList<>(topCards.entrySet());
            TopDisplayEntry entry = createTopDisplayEntry(i, chosenCards);
            topList.getChildren().add(entry);
        }
    }

    private TopDisplayEntry createTopDisplayEntry(int cost, List<Map.Entry<Card, Integer>> cards) {
        TopDisplayEntry entry = new TopDisplayEntry();
        List<Pair<IconImage, Integer>> iconImages = new ArrayList<>();
        for (Map.Entry<Card,Integer> e : cards)
        {
            IconImage iconImage = controllerDatabase.grabImage(e.getKey(), ThingType.CARD);
            iconImages.add(new Pair<>(iconImage, e.getValue()));
        }
        entry.initialize(cost, iconImages);
        return entry;
    }
}
