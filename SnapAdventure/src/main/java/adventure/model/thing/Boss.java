package adventure.model.thing;

import snapMain.model.constants.CampaignConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.thing.Card;

import java.util.Base64;

public class Boss {
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
        baseHP = 5;
        currentHP = baseHP;
        revealed = false;
    }

    public Boss(AdvCard c, int worldBonus)
    {
        card = c;
        baseHP = 5 + worldBonus;
        currentHP = baseHP;
        revealed = false;
    }

    public Boss(Boss boss) {
        card = boss.card;
        baseHP = boss.baseHP;
        currentHP = boss.currentHP;
        revealed = boss.revealed;
    }

   public int getID()
   {
       return card.getID();
   }
   
   public String getName()
   {
       return card.getName();
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
}
