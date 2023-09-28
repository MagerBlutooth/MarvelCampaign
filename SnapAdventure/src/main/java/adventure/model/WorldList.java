package adventure.model;

import campaign.model.constants.CampaignConstants;
import campaign.model.database.ThingDatabase;
import campaign.model.thing.ThingList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static adventure.model.AdventureConstants.NUMBER_OF_WORLDS;

public class WorldList extends ThingList<World> {

    public WorldList(AdventureDatabase database)
    {
        super(new ArrayList<>());
        BossList bosses = new BossList(database.getBosses());
        SectionList sections = new SectionList(database.getSections());
        bosses.shuffle();
        sections.shuffle();
        for(int i = 0; i < NUMBER_OF_WORLDS; i++)
        {
            Section s1 = sections.get(4*i);
            Section s2 = sections.get(4*i+1);
            Section s3 = sections.get(4*i+2);
            Section s4 = sections.get(4*i+3);
            Boss b = bosses.get(i);
            World world = new World(database, s1, s2, s3, s4, b);
            add(world);
        }

    }

    public WorldList(List<World> t) {
        super(t);
    }

    public List<World> getWorlds()
    {
        return getThings();
    }

    @Override
    public void sort() {

    }

    @Override
    public void setSortMode(String m) {

    }

    public String toSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(World w: getWorlds())
        {
            stringBuilder.append(Arrays.toString(w.toSaveStringArray()));
            stringBuilder.append(CampaignConstants.STRING_SEPARATOR);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(AdventureDatabase db, String cardString)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(cardString);
        String decodedString = new String(decodedBytes);
        String[] worldsList = decodedString.split(CampaignConstants.STRING_SEPARATOR);
        for(int i = 0; i < worldsList.length; i++)
        {
            World w = new World(db);
            w.fromSaveStringArray(worldsList);
            this.add(w);
        }
    }
}
