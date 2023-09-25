package records.model;

import campaign.model.constants.CampaignConstants;
import campaign.model.database.ThingDatabase;
import campaign.model.helper.ListHelper;
import campaign.model.thing.Card;
import campaign.model.thing.CardList;
import campaign.model.thing.Thing;
import campaign.model.thing.ThingType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HallOfFameEntry extends Thing {

    private static final int MAX_SIZE = 12;
    private static final int MAX_SHARED_CARDS = 3;
    Card captain;
    CardList cards;
    ThingDatabase<Card> cardDatabase;
    SnapMonth month;
    int year;

    public HallOfFameEntry(ThingDatabase<Card> db)
    {
        cards = new CardList(new ArrayList<>());
        cardDatabase = db;
        month = SnapMonth.JANUARY;
        year = Calendar.getInstance().get(Calendar.YEAR);
        captain = new Card();
    }
    public HallOfFameEntry(HallOfFameEntry e)
    {
        super(e);
        cards = e.getCards();
        cardDatabase = e.cardDatabase;
        captain = e.getCaptain();
        month = e.getMonth();
        year = e.getYear();
    }
    public HallOfFameEntry(ArrayList<Card> c, Card cpt, ThingDatabase<Card> db) {
        cards = new CardList(c);
        cardDatabase = db;
        month = SnapMonth.JANUARY;
        year = CampaignConstants.STARTING_YEAR;
        captain = cpt;
    }

    @Override
    public String[] toSaveStringArray() {
        StringBuilder attributeStrings = new StringBuilder();
        attributeStrings.substring(0,attributeStrings.length()); //Remove final separator
        return new String[]{String.valueOf(getID()), getName(), getMonth().toString(), getYear()+"", cards.toCSVSaveString(), String.valueOf(cards.getCardIndex(captain))};
    }

    @Override
    public ThingType getThingType() {
        return ThingType.HALL_OF_FAME;
    }

    @Override
    public void fromSaveStringArray(String[] mInfo) {
        setID(Integer.parseInt(mInfo[0]));
        setName(mInfo[1]);
        month = SnapMonth.valueOf(mInfo[2]);
        year = Integer.parseInt(mInfo[3]);
        cards.fromCSVSaveString(mInfo[4], cardDatabase);
        captain = cards.get(Integer.parseInt(mInfo[5]));
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

    @Override
    public HallOfFameEntry clone() {
        return new HallOfFameEntry(this);
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

    public CardList getCards() {
        return cards;
    }
    public boolean contains(Card card) {
        return cards.contains(card);
    }


    public boolean isValid() {
        return captain!=null && cards.size()==MAX_SIZE;
    }

    public SnapMonth getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }

    public void setMonth(SnapMonth m) {
        month = m;
    }

    public void setYear(int y)
    {
        year = y;
    }
}
