package adventure.model;

import adventure.model.stats.CardStatTracker;
import adventure.model.stats.CardStats;
import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import snapMain.model.target.StatusEffect;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class ExhaustionCalculator {

    public Map<ActiveCard, Boolean> checkExhaustion(ActiveCardList teamCards, CardStatTracker tracker)
    {
        ConcurrentHashMap<ActiveCard, Boolean> exhaustionMap = new ConcurrentHashMap<>();
        for(ActiveCard c: teamCards)
        {
            CardStats cardStat = tracker.lookupCardStat(c);
            boolean exhaust = false;
            int currentStreak = cardStat.getCurrentUseStreak();
            if(!c.hasStatus(StatusEffect.EXHAUSTED) && currentStreak > 1)
            {
                exhaust = calculateExhaustion(currentStreak);
            }
            exhaustionMap.put(c, exhaust);
        }
        return exhaustionMap;
    }

    private boolean calculateExhaustion(int currentStreak) {
        Random randomGen = new Random();
        int randomVal = randomGen.nextInt(10)+1;
        return randomVal < currentStreak;
    }
}
