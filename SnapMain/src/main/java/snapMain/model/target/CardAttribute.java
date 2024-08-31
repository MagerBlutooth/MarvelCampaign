package snapMain.model.target;

public enum CardAttribute {
    HERO("Hero"), VILLAIN("Villain"), ONREVEAL("On-Reveal"), ONGOING("Ongoing"), ACTIVATE("Activate"),
    MOVE("Move"), DISCARD("Discard"), DESTROY("Destroy"), VANILLA("Vanilla"), AVENGER("Avenger"),
    HYDRA("Hydra"), FANTASTIC_FOUR("Fantastic Four"), XMEN("X-Men"), GUARDIANS("Guardians of the Galaxy"),
    SPIDERMAN_ROGUE("Spider-Man Rogues Gallery"), WOUND_RESIST("Resists Wounds"), THUNDERBOLTS("Thunderbolts"),
    YOUNG_AVENGERS("Young Avengers");

    final String attributeString;
    CardAttribute(String s) {
        attributeString = s;
    }

    public String toString()
    {
        return attributeString;
    }
    public static CardAttribute parseString(String s)
    {
        for(CardAttribute attribute: values())
        {
            if(s.equals(attribute.attributeString))
                return attribute;
        }
        return null;
    }

}
