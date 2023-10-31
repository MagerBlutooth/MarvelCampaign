package adventure.model.target;

import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.TargetDatabase;
import snapMain.model.helper.StringHelper;
import snapMain.model.target.Playable;
import snapMain.model.target.TargetType;
import snapMain.model.target.Token;

public class InfinityStone implements Playable<Token> {

    int tokenId;
    InfinityStoneID stoneID;

    public InfinityStone()
    {
        tokenId = -1;
        stoneID = null;
    }

    public InfinityStone(InfinityStone i)
    {
        tokenId = i.tokenId;
        stoneID = i.stoneID;
    }

    public InfinityStone(int tId, InfinityStoneID sId)
    {
        tokenId = tId;
        stoneID = sId;
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
    public String toSaveString() {
        return getID() + SnapMainConstants.CSV_SEPARATOR + stoneID;
    }

    @Override
    public void fromSaveString (String mInfo, TargetDatabase<Token> tokens) {
        String[] splitString = mInfo.split(SnapMainConstants.CSV_SEPARATOR);
        setID(Integer.parseInt(splitString[0]));
        stoneID = InfinityStoneID.valueOf(splitString[1]);
    }

    @Override
    public String getEffect() {
        return stoneID.getEffect();
    }

    public String toString()
    {
        return StringHelper.toDisplayFormat(stoneID.toString());
    }
}
