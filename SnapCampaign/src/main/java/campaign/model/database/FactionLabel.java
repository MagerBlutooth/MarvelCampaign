package campaign.model.database;

public enum FactionLabel {
    SHIELD("Avengers Assemble", "Recruit 5 Free Agents from sets of 3. Once per Campaign."), HYDRA("Hail Hydra", "3 random Agents of SHIELD join your Faction. Once per Campaign."), FREE("",""), ENEMY("",""), UNKNOWN("","");

    String superPurchaseTitle;
    String superDescription;
    FactionLabel(String superPurchase, String superDesc)
    {
     superPurchaseTitle = superPurchase;
     superDescription = superDesc;
    }

    public String getSuperPurchaseTitle()
    {
        return superPurchaseTitle;
    }

    public String getSuperDescription() {
        return superDescription;
    }
}
