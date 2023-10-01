package snapMain.model.thing;

import snapMain.model.constants.CampaignConstants;

import java.util.ArrayList;

public class Location extends EffectBaseObject {
    boolean ruined;
    CardList stationedAgents;

    public Location()
    {
        super();
        name = "New Location";
        stationedAgents = new CardList(new ArrayList<>());
    }

    public Location(Location loc)
    {
        super(loc);
        ruined = loc.isRuined();
        stationedAgents = loc.getStationedAgents();
    }

    @Override
    public String[] toSaveStringArray() {
        return new String[]{String.valueOf(getID()), getName(), getEffect(), String.valueOf(isEnabled()), String.valueOf(isRuined())};
    }

    @Override
    public void fromSaveStringArray(String[] vInfo) {
        id = Integer.parseInt(vInfo[0]);
        name = vInfo[1];
        effect = vInfo[2];
        enabled = Boolean.parseBoolean(vInfo[3]);
        if(vInfo.length > 4)
            ruined = Boolean.parseBoolean(vInfo[4]);
    }

    @Override
    public boolean hasAttribute(String att) {
        return false;
    }

    public String toString()
    {
        return name;
    }

    public CardList getStationedAgents()
    {
        if(stationedAgents == null)
            return new CardList(new ArrayList<>());
        return stationedAgents;
    }

    public void removeStationedAgents() {
        if(stationedAgents != null)
            stationedAgents.clear();
    }

    public void stationAgent(Card c) {
        stationedAgents.add(c);
    }

    public boolean isFull() {
        return getStationedAgents().size() >= CampaignConstants.MAX_STATIONED_AGENTS;
    }

    public void removeStationedAgent(Card c) {
        stationedAgents.remove(c);
    }

    public void setRuined(boolean r)
    {
        ruined = r;
    }

    public boolean isRuined()
    {
        return ruined;
    }

    public void clearStationedAgents() {
        stationedAgents.clear();
    }

    @Override
    public Location clone() {
        return new Location(this);
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.LOCATION;
    }
}
