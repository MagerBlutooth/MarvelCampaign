package adventure.model.thing;

import snapMain.model.target.BaseObject;
import snapMain.model.target.Card;
import snapMain.model.target.TargetType;

public class AdvCard extends BaseObject {
    Card card;
    String effect;

    public AdvCard(Card c)
    {
        card = c;
        setName(c.getName());
        setID(c.getID());
        setEnabled(true);
        effect = "";
    }

    public AdvCard(AdvCard advCard) {
        card = advCard.card;
        setName(card.getName());
        setID(card.getID());
        setEnabled(advCard.isEnabled());
        effect = advCard.effect;
    }

    @Override
    public String[] toSaveStringArray() {
        return new String[]{ getID()+"", getName(), isEnabled()+"",getEffect()};
    }

    @Override
    public void fromSaveStringArray(String[] mInfo) {
        setEnabled(Boolean.parseBoolean(mInfo[2]));
        effect = mInfo[3];
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

}
