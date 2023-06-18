package model.thing;

import model.database.ThingDatabase;
import model.helper.ListHelper;

import java.util.ArrayList;
import java.util.List;

public class HallOfFameEntry extends Thing {

    private static final int MAX_SIZE = 12;
    private static final int MAX_SHARED_CARDS = 3;
    Card captain;
    CardList cards;
    ThingDatabase<Card> cardDatabase;

    public HallOfFameEntry(ThingDatabase<Card> db)
    {
        cards = new CardList(new ArrayList<>());
        cardDatabase = db;
        captain = new Card();
    }
    public HallOfFameEntry(ArrayList<Card> c, Card cpt, ThingDatabase<Card> db) {
        cards = new CardList(c);
        cardDatabase = db;
        captain = cpt;
    }

    @Override
    public String[] toSaveStringArray() {
        StringBuilder attributeStrings = new StringBuilder();
        attributeStrings.substring(0,attributeStrings.length()); //Remove final separator
        return new String[]{String.valueOf(getID()), getName(), cards.toCSVSaveString(), String.valueOf(cards.getCardIndex(captain))};
    }

    @Override
    public ThingType getThingType() {
        return ThingType.HALL_OF_FAME;
    }

    @Override
    public void fromSaveStringArray(String[] mInfo) {
        id = Integer.parseInt(mInfo[0]);
        name = mInfo[1];
        cards.fromCSVSaveString(mInfo[2], cardDatabase);
        captain = cards.get(Integer.parseInt(mInfo[3]));
    }

    public Card getCaptain() {
        return captain;
    }

    public void setCaptain(Card c)
    {
        captain = c;
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof HallOfFameEntry))
            return false;

        HallOfFameEntry h = (HallOfFameEntry) o;
        if(h.getEntrySize() != getEntrySize())
            return false;

        for(int i = 0; i < cards.size(); i++)
        {
            if(!(cards.get(i).equals(h.getCard(i))))
                return false;
        }
        return true;
    }

    @Override
    public boolean hasAttribute(String att) {
        return false;
    }

    public boolean addCard(Card c, List<HallOfFameEntry> otherEntries)
    {
        if(cards.size() < MAX_SIZE && deckValidWithNewCard(c, otherEntries)) {
            cards.add(c);
            cards.sort();
            return true;
        }
        return false;
    }

    private boolean deckValidWithNewCard(Card c, List<HallOfFameEntry> otherEntries) {
        List<Card> potentialNewDeck = new ArrayList<>(cards.getThings());
        potentialNewDeck.add(c);
        for(HallOfFameEntry entry: otherEntries)
        {
            List<Card> entryCards = new ArrayList<>(entry.getThings());
            List<Card> commonValues = ListHelper.getCommonValues(potentialNewDeck, entryCards);
            if(commonValues.size() > MAX_SHARED_CARDS)
                return false;
        }
        return true;
    }

    private List<Card> getThings() {
        return cards.getThings();
    }

    public boolean removeCard(Card c)
    {
        cards.remove(c);
        cards.sort();
        return true;
    }

    private Card getCard(int i) {
        return cards.get(i);
    }
    private int getEntrySize()
    {
        return cards.size();
    }

    public ThingList<Card> getCards() {
        return cards;
    }
    public boolean contains(Card card) {
        return cards.contains(card);
    }


    public boolean isValid() {
        return captain!=null && cards.size()==MAX_SIZE;
    }
}
