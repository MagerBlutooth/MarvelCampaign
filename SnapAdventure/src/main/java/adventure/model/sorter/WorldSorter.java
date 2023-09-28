package adventure.model.sorter;

import adventure.model.World;
import campaign.model.sortFilter.WorldSortMode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WorldSorter {

    WorldSortMode worldSortMode;

    public WorldSorter()
    {
        worldSortMode = WorldSortMode.WORLD_NUM;
    }

    public List<World> sort(List<World> worlds)
    {
        List<World> sortedWorlds = new ArrayList<>(worlds);
        switch(worldSortMode)
        {
            case WORLD_NUM:
                sortedWorlds.sort((o1, o2) -> Comparator.comparing(World::getName).compare(o1, o2));
                break;
        }
        return sortedWorlds;
    }

    public void changeMode(WorldSortMode b)
    {
        worldSortMode = b;
    }
}
