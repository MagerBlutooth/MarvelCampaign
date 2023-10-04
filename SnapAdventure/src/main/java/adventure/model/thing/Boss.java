package adventure.model.thing;

import snapMain.model.constants.CampaignConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.Card;
import snapMain.model.target.SnapTarget;
import snapMain.model.target.TargetType;

import java.util.Base64;

public class Boss implements SnapTarget {
    AdvCard card;
    int baseHP;
    int currentHP;
    boolean revealed;

    public Boss()
    {
        card = new AdvCard(new Card());
    }

    public Boss(AdvCard c)
    {
        card = c;
        setBaseHP(5);
        currentHP = baseHP;
        revealed = false;
    }

    public Boss(AdvCard c, int worldBonus)
    {
        card = c;
        setBaseHP(5 + worldBonus);
        currentHP = baseHP;
        revealed = false;
    }

    public Boss(Boss boss) {
        card = boss.card;
        setBaseHP(boss.baseHP);
        currentHP = boss.currentHP;
        revealed = boss.revealed;
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.ADV_CARD;
    }

    public int getID()
   {
       return card.getID();
   }

    @Override
    public void setEnabled(boolean enabled) {

    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public String getName()
   {
       return card.getName();
   }

    @Override
    public void setID(int id) {

    }

    @Override
    public boolean hasAttribute(String entry) {
        return card.hasAttribute(entry);
    }

    @Override
    public Boss clone() {
        return new Boss(this);
    }

    public String toSaveString() {
        String result = getID() +
                CampaignConstants.SUBCATEGORY_SEPARATOR +
                baseHP +
                CampaignConstants.SUBCATEGORY_SEPARATOR +
                currentHP +
                CampaignConstants.SUBCATEGORY_SEPARATOR +
                revealed;
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String saveString, TargetDatabase<AdvCard> cards)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(saveString);
        String decodedString = new String(decodedBytes);
        if(decodedString.isBlank())
            return;
        String[] stringList = decodedString.split(CampaignConstants.SUBCATEGORY_SEPARATOR);
        card = cards.lookup(Integer.parseInt(stringList[0]));
        baseHP = Integer.parseInt(stringList[1]);
        currentHP = Integer.parseInt(stringList[2]);
        revealed = Boolean.parseBoolean(stringList[3]);
    }

    public AdvCard getCard() {
        return card;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void reveal() {
        revealed = true;
    }

    public String getEffect() {
        return card.getEffect();
    }

    public void setCurrentHP(int newHP) {
        currentHP = newHP;
    }

    private void setBaseHP(int h) {
        if (h <= 0)
            h = 1;
        baseHP = h;
    }

    public int getCurrentHP()
    {
        return currentHP;
    }

    public int getBaseHP() {
        return baseHP;
    }
}
