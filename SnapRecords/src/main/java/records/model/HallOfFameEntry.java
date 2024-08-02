package records.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.helper.ListHelper;
import snapMain.model.target.*;

import java.util.*;

public class HallOfFameEntry extends BaseObject {

    private static final int MAX_SHARED_CARDS = 3;
    private static final int MIN_UNIQUE_CARDS = 5;

    Card captain;
    CardList cards;
    TargetDatabase<Card> cardDatabase;
    SnapMonth month;
    int year;
    IntegerProperty uniqueCardCountProperty;
    IntegerProperty cardCountProperty;
    BooleanProperty captainSetProperty;

    public HallOfFameEntry()
    {
        super();
        cards = new CardList(new ArrayList<>());
        captain = new Card();
        month = SnapMonth.JANUARY;
        year = Calendar.getInstance().get(Calendar.YEAR);
        uniqueCardCountProperty = new SimpleIntegerProperty(0);
        cardCountProperty = new SimpleIntegerProperty(0);
        captainSetProperty = new SimpleBooleanProperty(false);
    }

    public HallOfFameEntry(TargetDatabase<Card> db)
    {
        this();
        cardDatabase = db;
    }
    public HallOfFameEntry(HallOfFameEntry e)
    {
        super(e);
        cards = e.getCards();
        cardDatabase = e.cardDatabase;
        captain = e.getCaptain();
        month = e.getMonth();
        year = e.getYear();
        uniqueCardCountProperty = e.getUniqueCardCountProperty();
        cardCountProperty = e.getCardCountProperty();
        captainSetProperty = e.getCaptainSetProperty();

    }

    @Override
    public String[] toCSVSaveStringArray() {
        StringBuilder attributeStrings = new StringBuilder();
        attributeStrings.substring(0,attributeStrings.length()); //Remove final separator
        return new String[]{String.valueOf(getID()), getName(), getMonth().toString(), getYear()+"",
                cards.toCSVSaveString(), String.valueOf(cards.getCardIndex(captain))};
    }

    @Override
    public void fromCSVSaveStringArray(String[] mInfo) {
        setID(Integer.parseInt(mInfo[0]));
        setName(mInfo[1]);
        month = SnapMonth.valueOf(mInfo[2]);
        year = Integer.parseInt(mInfo[3]);
        cards.fromCSVSaveString(mInfo[4], cardDatabase);
        captain = cards.get(Integer.parseInt(mInfo[5]));
    }

    public void checkCardCountProperties(List<HallOfFameEntry> otherEntries)
    {
        List<Card> potentialNewDeck = new ArrayList<>(cards.getCards());
        Set<Card> allCardSet = new LinkedHashSet<>();
        for(HallOfFameEntry entry: otherEntries) {
            allCardSet.addAll(entry.getThings());
        }
        List<Card> allCardList = new ArrayList<>(allCardSet);

        uniqueCardCountProperty.set(ListHelper.getNewValues(potentialNewDeck, allCardList).size());
        cardCountProperty.set(getEntrySize());
    }

    public Card getCaptain() {
        return captain;
    }

    public void setCaptain(Card c)
    {
        captain = c;
        captainSetProperty.set(captain.isActualThing());
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

    public DeckCheckResult addCard(Card c, List<HallOfFameEntry> otherEntries)
    {
        DeckCheckResult validityCheck = deckValidWithNewCard(c, otherEntries);
        if(cards.size() < SnapMainConstants.DECK_SIZE && validityCheck.getResult()) {
            cards.add(c);
            cards.sort();
        }
        return validityCheck;
    }

    private DeckCheckResult deckValidWithNewCard(Card c, List<HallOfFameEntry> otherEntries) {
        List<Card> potentialNewDeck = new ArrayList<>(cards.getThings());
        potentialNewDeck.add(c);
        for(HallOfFameEntry entry: otherEntries)
        {
            List<Card> entryCards = new ArrayList<>(entry.getThings());
            CardList commonValues = new CardList(ListHelper.getCommonValues(potentialNewDeck, entryCards));
            if(commonValues.size() > MAX_SHARED_CARDS)
                return new DeckCheckResult(commonValues, false, uniqueCardCountProperty.get());
        }
        return new DeckCheckResult(true, uniqueCardCountProperty.get());
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


    public boolean isSavable() {
        return captainSetProperty.get() && cardCountProperty.get()==SnapMainConstants.DECK_SIZE &&
                uniqueCardCountProperty.get() >= MIN_UNIQUE_CARDS;
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

    @Override
    public TargetType getTargetType() {
        return TargetType.HALL_OF_FAME;
    }

    public int getMinUniqueCardCount() {
        return MIN_UNIQUE_CARDS;
    }

    public IntegerProperty getUniqueCardCountProperty() {
        return uniqueCardCountProperty;
    }

    public IntegerProperty getCardCountProperty() {
        return cardCountProperty;
    }

    public BooleanProperty getCaptainSetProperty() {
        return captainSetProperty;
    }

    public boolean notEnoughUniqueCards() {
        return uniqueCardCountProperty.get() < MIN_UNIQUE_CARDS;
    }
}
