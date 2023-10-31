package snapMain.model.sortFilter;

import snapMain.model.target.Card;
import snapMain.model.target.CardAttribute;
import snapMain.model.target.CardList;

import java.util.ArrayList;
import java.util.List;

public class CardFilter {

    public CardList filterCost(CardList cardList, int minCost, int maxCost)
    {
        CardList filteredList = new CardList(new ArrayList<>());
        for(Card c: cardList)
        {
            if(minCost <= c.getCost() && c.getCost() <= maxCost)
                filteredList.add(c);
        }
        return filteredList;
    }
    public CardList filterPower(CardList cardList, int minPower, int maxPower)
    {
        CardList filteredList = new CardList(new ArrayList<>());
        for(Card c: cardList)
        {
            if(minPower <= c.getPower() && c.getPower() <= maxPower)
                filteredList.add(c);
        }
        return filteredList;
    }

    public CardList filterPool(CardList cardList, int minPool, int maxPool)
    {
        CardList filteredList = new CardList(new ArrayList<>());
        for(Card c: cardList)
        {
            if(minPool <= c.getPower() && c.getPower() <= maxPool)
                filteredList.add(c);
        }
        return filteredList;
    }

    public CardList filterAttributes(CardList cardList, List<CardAttribute> cardAttributes)
    {
        CardList filteredList = new CardList(new ArrayList<>());
        for(Card c: cardList)
        {
            if(c.hasAnyAttributes(cardAttributes))
                filteredList.add(c);
        }
        return filteredList;
    }
}
