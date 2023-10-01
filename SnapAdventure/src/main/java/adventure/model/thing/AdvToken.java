package adventure.model.thing;

import snapMain.model.thing.BaseObject;
import snapMain.model.thing.TargetType;
import snapMain.model.thing.Token;

public class AdvToken extends BaseObject {
    Token token;

    @Override
    public String[] toSaveStringArray() {
        return new String[0];
    }

    @Override
    public void fromSaveStringArray(String[] mInfo) {

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
    public BaseObject clone() {
        return null;
    }
}
