package adventure.model.target;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureConstants;
import adventure.model.target.base.AdvCard;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.*;

import java.util.Base64;

public class Enemy implements SnapTarget {
    Playable subject;
    Playable secondarySubject;
    int baseHP;
    int missingHP;

    public Enemy()
    {
        subject = new AdvCard();
        secondarySubject = new AdvCard();
        subject.setID(SnapMainConstants.MOOK_ICON_ID);
        secondarySubject.setID(SnapMainConstants.MOOK_ICON_ID);
        setBaseHP(AdventureConstants.MOOK_BASE_HP);
        missingHP = 0;
    }

    public Enemy(Playable p)
    {
        this();
        subject = p;
    }

    public Enemy(Playable p, int worldBonus)
    {
        this();
        subject = p;
        setBaseHP(worldBonus);
    }

    public Enemy(Enemy nme) {
        subject = nme.subject;
        secondarySubject = nme.secondarySubject;
        setBaseHP(nme.baseHP);
        missingHP = nme.missingHP;
    }

    public void setSecondarySubject(Playable p)
    {
        secondarySubject = p;
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
                missingHP;
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
        else if(targetType == TargetType.TOKEN)
            targetDatabase = database.lookupDatabase(TargetType.TOKEN);
        subject = (Playable)targetDatabase.lookup(id);
        baseHP = Integer.parseInt(stringList[2]);
        missingHP = Integer.parseInt(stringList[3]);
    }

    public SnapTarget getSubject() {
        return subject;
    }

    public String getEffect() {
        return subject.getEffect();
    }

    public void gainHP(int hp)
    {
        missingHP = Math.max(0, missingHP - hp);
    }

    public void loseHP(int hp)
    {
        missingHP = missingHP + hp;
    }

    public void setBaseHP(int h) {
        if (h <= 0)
            h = 1;
        baseHP = h;
    }

    public int getCurrentHP()
    {
        return baseHP - missingHP;
    }

    public int getBaseHP() {
        return baseHP;
    }

    public Playable getSecondarySubject() {
        return secondarySubject;
    }

    public String toString()
    {
        return getName();
    }
}
