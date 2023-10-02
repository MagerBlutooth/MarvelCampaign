package adventure.model;

import adventure.model.thing.AdvCard;
import adventure.model.thing.AdvCardList;
import adventure.model.thing.AdvLocation;
import adventure.model.thing.AdvLocationList;
import snapMain.model.constants.CampaignConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static adventure.model.AdventureConstants.NUMBER_OF_WORLDS;

public class WorldList extends ArrayList<World> {

    public WorldList(AdventureDatabase database)
    {
        super(new ArrayList<>());
        AdvCardList bosses = new AdvCardList(database.getBosses());
        AdvLocationList sections = new AdvLocationList(database.getSections());
        bosses.shuffle();
        sections.shuffle();
        for(int i = 0; i < NUMBER_OF_WORLDS; i++)
        {
            AdvLocation s1 = sections.get(4*i);
            AdvLocation s2 = sections.get(4*i+1);
            AdvLocation s3 = sections.get(4*i+2);
            AdvLocation s4 = sections.get(4*i+3);
            List<AdvLocation> locations = new ArrayList<>();
            locations.add(s1);
            locations.add(s2);
            locations.add(s3);
            locations.add(s4);
            AdvCard b = bosses.get(i);
            World world = new World(database, locations, b, i);
            add(world);
        }

    }

    public WorldList(List<World> t) {
        super(t);
    }

    public String toSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(World w: this)
        {
            stringBuilder.append(w.toSaveString());
            stringBuilder.append(CampaignConstants.STRING_SEPARATOR);
        }
        if(!stringBuilder.isEmpty())
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(AdventureDatabase db, AdvMainDatabase mainDB, String cardString)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(cardString);
        String decodedString = new String(decodedBytes);
        String[] worldsList = decodedString.split(CampaignConstants.STRING_SEPARATOR);
        for (String s : worldsList) {
            World w = new World(db);
            w.fromSaveString(s, mainDB);
            this.add(w);
        }
    }

    public List<AdvLocation> getAllLocations() {
        List<AdvLocation> allLocations = new ArrayList<>();
        for(World w: this)
        {
            allLocations.add(w.getFirstSection().getLocation());
            allLocations.add(w.getSecondSection().getLocation());
            allLocations.add(w.getThirdSection().getLocation());
            allLocations.add(w.getFourthSection().getLocation());
        }
        return allLocations;
    }

    public List<AdvCard> getAllBosses() {
        List<AdvCard> allBosses = new ArrayList<>();
        for(World w: this)
        {
            allBosses.add(w.getBoss().getCard());
        }
        return allBosses;
    }
}
