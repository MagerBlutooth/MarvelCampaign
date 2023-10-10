package adventure.model.thing;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureConstants;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.*;

import java.util.Base64;

public class Enemy implements SnapTarget {
    Playable subject;
    int baseHP;
    int currentHP;

    public Enemy()
    {
        subject = new Card();
    }

    public Enemy(int worldBonus)
    {
        Token t = new Token();
        t.setID(SnapMainConstants.MOOK_ICON_ID);
        subject = t;
        baseHP = worldBonus;
        currentHP = baseHP;
    }

    public Enemy(Playable p)
    {
        subject = p;
        setBaseHP(AdventureConstants.MOOK_BASE_HP);
        currentHP = baseHP;
    }

    public Enemy(Playable p, int worldBonus)
    {
        subject = p;
        setBaseHP(5 + worldBonus);
        currentHP = baseHP;
    }

    public Enemy(Enemy nme) {
        subject = nme.subject;
        setBaseHP(nme.baseHP);
        currentHP = nme.currentHP;
    }

    @Override
    public TargetType getTargetType() {
        return getSubject().getTargetType();
    }

    public int getID()
   {
       return subject.getID();
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
       return subject.getName();
   }

    @Override
    public void setID(int id) {

    }

    @Override
    public boolean hasAttribute(String entry) {
        return subject.hasAttribute(entry);
    }

    @Override
    public Enemy clone() {
        return new Enemy(this);
    }

    public String toSaveString() {
        String result = getTargetType() + SnapMainConstants.SUBCATEGORY_SEPARATOR + getID() +
                SnapMainConstants.SUBCATEGORY_SEPARATOR +
                baseHP +
                SnapMainConstants.SUBCATEGORY_SEPARATOR +
                currentHP;
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String saveString, AdvMainDatabase database)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(saveString);
        String decodedString = new String(decodedBytes);
        if(decodedString.isBlank())
            return;
        String[] stringList = decodedString.split(SnapMainConstants.SUBCATEGORY_SEPARATOR);
        TargetType targetType = TargetType.valueOf(stringList[0]);
        int id = Integer.parseInt(stringList[1]);
        TargetDatabase<SnapTarget> targetDatabase = new TargetDatabase<>();
        if(targetType == TargetType.CARD)
            targetDatabase = database.lookupDatabase(TargetType.ADV_CARD);
        subject = (Playable)targetDatabase.lookup(id);
        baseHP = Integer.parseInt(stringList[2]);
        currentHP = Integer.parseInt(stringList[3]);
    }

    public SnapTarget getSubject() {
        return subject;
    }

    public String getEffect() {
        return subject.getEffect();
    }

    public void setCurrentHP(int newHP) {
        currentHP = newHP;
    }

    public void setBaseHP(int h) {
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

    public boolean hpDepleted() {
        return currentHP <= 0;
    }
}
