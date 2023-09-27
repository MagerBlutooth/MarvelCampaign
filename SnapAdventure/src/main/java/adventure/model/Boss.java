package adventure.model;

import campaign.model.constants.CampaignConstants;
import campaign.model.thing.Card;
import campaign.model.thing.CardAttribute;
import campaign.model.thing.Thing;
import campaign.model.thing.ThingType;

import java.util.Map;

public class Boss extends Thing {
    Card card;
    String effect;

    public Boss(Card c)
    {
        card = c;
        setName(c.getName());
        setID(c.getID());
        setEnabled(true);
        effect = "";
    }

    public Boss(Boss boss) {
        card = boss.card;
        setName(card.getName());
        setID(card.getID());
        setEnabled(boss.isEnabled());
        effect = boss.effect;
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
    public ThingType getThingType() {
        return ThingType.CARD;
    }

    @Override
    public boolean hasAttribute(String att) {
        return card.hasAttribute(att);
    }

    @Override
    public Boss clone() {
        return new Boss(this);
    }

    public void setEffect(String e) {
        effect = e;
    }

    public Card getCard() {
        return card;
    }
}
