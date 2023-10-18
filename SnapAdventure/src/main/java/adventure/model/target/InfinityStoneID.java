package adventure.model.target;

public enum InfinityStoneID {
    POWER_STONE(5, "Increase damage done to bosses and mooks by 1"),
    MIND_STONE(24, "Once per world, you may draft an additional card."),
    REALITY_STONE(6, "Once per world, you may reroll the current location."),
    TIME_STONE(13, "Once per section, you may revert the results of a match."),
    SOUL_STONE(8, "Once per world, you may revive one of your eliminated cards."),
    SPACE_STONE(9, "Once per match, you may add a card to your deck from anywhere, ignoring deck restrictions. " +
            "If it was stationed, return it after the next match.");
    final int id;
    final String effect;
    InfinityStoneID(int i, String e)
    {
      id = i;
      effect = e;
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

    public String getEffect() {
        return effect;
    }
}
