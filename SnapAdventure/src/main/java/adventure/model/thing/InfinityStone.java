package adventure.model.thing;

import snapMain.model.helper.StringHelper;
import snapMain.model.target.Playable;
import snapMain.model.target.SnapTarget;
import snapMain.model.target.TargetType;
import snapMain.model.target.Token;

public class InfinityStone implements SnapTarget, Playable {

    int tokenId;
    InfinityStoneID stoneID;

    public InfinityStone(int tId, InfinityStoneID sId)
    {
        tokenId = tId;
        stoneID = sId;
    }

    public static boolean checkToken(int id) {
        for(InfinityStoneID sid: InfinityStoneID.values())
        {
           if(sid.getID() == id)
               return true;
        }
        return false;
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.TOKEN;
    }

    @Override
    public int getID() {
        return tokenId;
    }

    public String getPrettyString()
    {
        String s = stoneID.name().toLowerCase();
        return StringHelper.camelCase(s);
    }

    @Override
    public void setEnabled(boolean enabled) {
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return getPrettyString();
    }

    @Override
    public void setID(int id) {
        tokenId = id;
    }

    @Override
    public boolean hasAttribute(String entry) {
        return false;
    }

    @Override
    public String[] toSaveStringArray() {
        return new String[]{ getID()+"", stoneID+""};
    }

    @Override
    public void fromSaveStringArray (String[] mInfo) {
        setID(Integer.parseInt(mInfo[0]));
        stoneID = InfinityStoneID.valueOf(mInfo[1]);
    }
}
