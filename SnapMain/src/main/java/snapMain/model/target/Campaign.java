package snapMain.model.target;

import snapMain.model.database.FactionLabel;
import snapMain.model.helper.Randomizer;
import javafx.scene.paint.Color;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.CampaignDatabase;

import java.util.ArrayList;
import java.util.List;

public class Campaign {

    Faction shield;
    Faction hydra;
    Randomizer randomizer = new Randomizer();
    CampaignDatabase campaignDatabase;

    public Campaign(CampaignDatabase db, Faction s, Faction h)
    {
        campaignDatabase = db;
        shield = s;
        hydra = h;
    }

    private CardList getFreeAgents() {
        CardList freeAgents = new CardList(campaignDatabase.getCards());
        CardList ownedAgents = new CardList(new ArrayList<>());
        if(shield != null)
            ownedAgents.addAll(shield.getAgentCards());
        if(hydra != null)
            ownedAgents.addAll(hydra.getAgentCards());
        freeAgents.removeAll(ownedAgents.getCards());
        return freeAgents;
    }

    private LocationList getFreeLocations() {
        LocationList freeLocations = new LocationList(campaignDatabase.getLocations());
        LocationList ownedLocations = new LocationList(new ArrayList<>());
        if(shield != null)
            ownedLocations.addAll(shield.getLocationObjects());
        if(hydra !=null)
           ownedLocations.addAll(hydra.getLocationObjects());
        freeLocations.removeAll(ownedLocations.getLocations());
        return freeLocations;
    }

    List<Card> getAllAgents()
    {
      List<Card> allAgents = new ArrayList<>();
      allAgents.addAll(shield.getAgentCards());
      allAgents.addAll(hydra.getAgentCards());
      allAgents.addAll(getFreeFaction().getAgentCards());
      return allAgents;
    }

    List<Location> getAllLocations()
    {
        List<Location> allLocations = new ArrayList<>();
        allLocations.addAll(shield.getLocationObjects());
        allLocations.addAll(hydra.getLocationObjects());
        allLocations.addAll(getFreeFaction().getLocationObjects());
        return allLocations;
    }

    public Card randomShieldAgent()
    {
        return randomizer.getRandomElement(shield.getAgentCards());
    }

    public Card randomHydraAgent()
    {
        return randomizer.getRandomElement(hydra.getAgentCards());
    }

    public Card randomAgent()
    {
        List<Card> allAgents = getAllAgents();
        return randomizer.getRandomElement(allAgents);
    }

    public Location randomLocation()
    {
        List<Location> allLocations = getAllLocations();
        return randomizer.getRandomElement(allLocations);
    }

    public Faction getShield()
    {
        return shield;
    }

    public Faction getHydra()
    {
        return hydra;
    }
    public Faction getFreeFaction() {
        CardList freeAgents = getFreeAgents();
        LocationList freeLocations = getFreeLocations();
        return new Faction(FactionLabel.FREE, freeAgents, freeLocations, campaignDatabase);}
    public Faction getFaction(Faction f) {
        if(f.equals(shield))
            return shield;
        if(f.equals(hydra))
            return hydra;
        return getFreeFaction();
    }

    public Location randomShieldLocation() {
        return randomizer.getRandomElement(shield.getOwnedLocations().getThings());
    }
    public Location randomHydraLocation() {
        return randomizer.getRandomElement(hydra.getLocationObjects());
    }

    public Card randomFreeAgent() {
        return randomizer.getRandomElement(getFreeFaction().getAgentCards());
    }
    public Location randomFreeLocation()
    {
        return randomizer.getRandomElement(getFreeFaction().getLocationObjects());
    }

    public Color getColor(BaseObject t) {
        if(shield.ownsThing(t))
        {
            return SnapMainConstants.SHIELD_COLOR;
        }
        else if(hydra.ownsThing(t))
        {
            return SnapMainConstants.HYDRA_COLOR;
        }
        else
            return SnapMainConstants.FREE_COLOR;
    }

    public List<Card> randomMercs() {
        int MERC_SELECT_POOL = 3;
        return randomizer.getRandomElements(getFreeFaction().getAgentCards(), MERC_SELECT_POOL);
    }
}
