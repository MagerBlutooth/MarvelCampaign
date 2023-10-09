package adventure.model.thing;

public enum InfinityStoneID {
    POWER_STONE(5), MIND_STONE(24), REALITY_STONE(6), TIME_STONE(13), SOUL_STONE(8), SPACE_STONE(9);
    final int id;
    InfinityStoneID(int i)
    {
      id = i;
    }

    public static InfinityStoneID lookupID(int id) {
        for(InfinityStoneID sid: values())
        {
            if(sid.getID() == id)
                return sid;
        }
        return null;
    }

    public int getID()
    {
        return id;
    }
}
