package adventure.model.thing;

import snapMain.model.constants.CampaignConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.thing.Card;

import java.util.Base64;

public class Boss {
    AdvCard card;
    int baseHP;
    int currentHP;

    public Boss()
    {
        card = new AdvCard(new Card());
    }

    public Boss(AdvCard c)
    {
        card = c;
        baseHP = 5;
        currentHP = baseHP;
    }

    public Boss(AdvCard c, int worldBonus)
    {
        card = c;
        baseHP = 5 + worldBonus;
        currentHP = baseHP;
    }

    public Boss(Boss boss) {
        card = boss.card;
        baseHP = boss.baseHP;
        currentHP = boss.currentHP;
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
                CampaignConstants.CATEGORY_SEPARATOR +
                baseHP +
                CampaignConstants.CATEGORY_SEPARATOR +
                currentHP;
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String saveString, TargetDatabase<AdvCard> cards)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(saveString);
        String decodedString = new String(decodedBytes);
        if(decodedString.isBlank())
            return;
        String[] stringList = decodedString.split(CampaignConstants.SUBCATEGORY_SEPARATOR);
        card = cards.lookup(stringList[0]);
        baseHP = Integer.parseInt(stringList[1]);
        currentHP = Integer.parseInt(stringList[2]);
    }

    public AdvCard getCard() {
        return card;
    }
}
