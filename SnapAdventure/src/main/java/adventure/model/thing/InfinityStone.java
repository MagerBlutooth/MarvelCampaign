package adventure.model.thing;

import snapMain.model.helper.StringHelper;
import snapMain.model.target.Playable;
import snapMain.model.target.Target;
import snapMain.model.target.TargetType;
import snapMain.model.target.Token;

public class InfinityStone implements Target, Playable {

    Token infinityStone;
    InfinityStoneID stoneID;

    public InfinityStone(Token t, InfinityStoneID id)
    {
        infinityStone = t;
        stoneID = id;
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.TOKEN;
    }

    @Override
    public int getID() {
        return infinityStone.getID();
    }

    public String getPrettyString()
    {
        String s = stoneID.name().toLowerCase();
        return StringHelper.displayFormat(s);
    }

    @Override
    public void setEnabled(boolean enabled) {

    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setID(int id) {

    }

    @Override
    public String[] toSaveStringArray() {
        return new String[0];
    }

    @Override
    public void fromSaveStringArray(String[] mInfo) {

    }
}
