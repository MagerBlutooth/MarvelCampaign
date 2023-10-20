package adventure.model.target.base;

import snapMain.model.constants.SnapMainConstants;
import snapMain.model.target.*;
import snapMain.model.database.TargetDatabase;

public class AdvCard extends BaseObject implements Playable<Card> {
    Card card;
    String effect;

    public AdvCard()
    {
        card = new Card();
        setEnabled(true);
        effect = "";
    }

    public AdvCard(Card c)
    {
        this();
        setName(c.getName());
        setID(c.getID());
        card = c;
    }

    public AdvCard(AdvCard advCard) {
        card = advCard.card;
        setName(card.getName());
        setID(card.getID());
        setEnabled(advCard.isEnabled());
        effect = advCard.effect;
    }

    @Override
    public String[] toCSVSaveStringArray() {
        return new String[]{ getID()+"", getName(), isEnabled()+"",getEffect()};
    }

    @Override
    public void fromCSVSaveStringArray(String[] mInfo) {
        setID(Integer.parseInt(mInfo[0]));
        setName(mInfo[1]);
        setEnabled(Boolean.parseBoolean(mInfo[2]));
        effect = mInfo[3];
    }

    @Override
    public String toSaveString() {
        return String.valueOf(getID());
    }

    @Override
    public void fromSaveString(String mInfo, TargetDatabase<Card> things) {
        card = things.lookup(Integer.parseInt(mInfo));
    }

    public String getEffect()
    {
        return effect;
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.CARD;
    }

    @Override
    public boolean hasAttribute(String att) {
        return card.hasAttribute(att);
    }

    @Override
    public AdvCard clone() {
        return new AdvCard(this);
    }

    public void setEffect(String e) {
        effect = e;
    }

    public Card getCard() {
        return card;
    }

    public String toString()
    {
        return card.getName();
    }
}