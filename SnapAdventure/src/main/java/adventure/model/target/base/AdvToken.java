package adventure.model.target.base;

import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.BaseObject;
import snapMain.model.target.Playable;
import snapMain.model.target.TargetType;
import snapMain.model.target.Token;

public class AdvToken extends BaseObject implements Playable<Token> {
    Token token;
    String effect;

    public AdvToken()
    {
        token = null;
        token = new Token();
        effect = "";
    }

    public AdvToken(Token t)
    {
        token = t;
        setName(t.getName());
        setID(t.getID());
        setEnabled(true);
        effect = "";
    }

    public AdvToken(AdvToken advToken) {
        token = advToken.token;
        setName(advToken.getName());
        setID(advToken.getID());
        setEnabled(advToken.isEnabled());
        effect = advToken.effect;
    }

    public void setName(String n)
    {
        token.setName(n);
    }

    @Override
    public String toSaveString() {
        String stringBuilder = getID() + SnapMainConstants.CSV_SEPARATOR +
                getName() + SnapMainConstants.CSV_SEPARATOR +
                isEnabled() + SnapMainConstants.CSV_SEPARATOR +
                getEffect();
        return stringBuilder;
    }

    @Override
    public void fromSaveString(String mInfo, TargetDatabase<Token> tokens) {
        String[] splitString = mInfo.split(SnapMainConstants.CSV_SEPARATOR);
        token = tokens.lookup(Integer.parseInt(splitString[0]));
        setEnabled(Boolean.parseBoolean(splitString[2]));
        effect = splitString[3];
    }

    @Override
    public String getEffect() {
        return effect;
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.TOKEN;
    }

    @Override
    public String[] toCSVSaveStringArray() {
        return new String[]{ getID()+"", getName(), isEnabled()+"",getEffect()};
    }

    @Override
    public int getID() {
        return token.getID();
    }

    @Override
    public void fromCSVSaveStringArray(String[] mInfo) {
        setID(Integer.parseInt(mInfo[0]));
        setName(mInfo[1]);
        setEnabled(Boolean.parseBoolean(mInfo[2]));
        effect = mInfo[3];
    }

    @Override
    public void setEnabled(boolean enabled) {
        token.setEnabled(enabled);
    }

    @Override
    public boolean isEnabled() {
        return token.isEnabled();
    }

    @Override
    public String getName() {
        return token.getName();
    }

    @Override
    public void setID(int id) {
        token.setID(id);
    }

    @Override
    public boolean hasAttribute(String att) {
        return false;
    }

    @Override
    public AdvToken clone() {
        return new AdvToken(this);
    }

    public Token getToken() {
        return token;
    }

    public void setEffect(String e) {
        effect = e;
    }

    public String toString()
    {
        return getName();
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof AdvToken oToken))
            return false;
        return getID() == oToken.getID();
    }
}
