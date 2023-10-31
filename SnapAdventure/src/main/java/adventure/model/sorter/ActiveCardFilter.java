package adventure.model.sorter;

import adventure.model.Team;
import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import snapMain.model.target.CardAttribute;

import java.util.ArrayList;
import java.util.List;

public class ActiveCardFilter {

    public ActiveCardList filterCost(ActiveCardList cardList, int minCost, int maxCost)
    {
        ActiveCardList filteredList = new ActiveCardList(new ArrayList<>());
        for(ActiveCard c: cardList)
        {
            if(minCost <= c.getCost() && c.getCost() <= maxCost)
                filteredList.add(c);
        }
        return filteredList;
    }
    public ActiveCardList filterPower(ActiveCardList cardList, int minPower, int maxPower)
    {
        ActiveCardList filteredList = new ActiveCardList(new ArrayList<>());
        for(ActiveCard c: cardList)
        {
            if(minPower <= c.getPower() && c.getPower() <= maxPower)
                filteredList.add(c);
        }
        return filteredList;
    }

    public ActiveCardList filterPool(ActiveCardList cardList, int minPool, int maxPool)
    {
        ActiveCardList filteredList = new ActiveCardList(new ArrayList<>());
        for(ActiveCard c: cardList)
        {
            if(minPool <= c.getPower() && c.getPower() <= maxPool)
                filteredList.add(c);
        }
        return filteredList;
    }

    public ActiveCardList filterAttributes(ActiveCardList cardList, List<CardAttribute> cardAttributes)
    {
        ActiveCardList filteredList = new ActiveCardList(new ArrayList<>());
        for(ActiveCard c: cardList)
        {
            if(c.hasAnyAttributes(cardAttributes))
                filteredList.add(c);
        }
        return filteredList;
    }
}
