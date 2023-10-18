package adventure.model.target;

import snapMain.model.target.BaseObject;
import snapMain.model.target.Playable;
import snapMain.model.target.TargetType;
import snapMain.model.target.Token;

public class AdvToken extends BaseObject implements Playable {
    Token token;
    String effect;

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

    @Override
    public String[] toSaveStringArray() {
        return new String[]{ getID()+"", getName(), isEnabled()+"",getEffect()};
    }

    @Override
    public void fromSaveStringArray(String[] mInfo) {
        setEnabled(Boolean.parseBoolean(mInfo[2]));
        effect = mInfo[3];
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
}
