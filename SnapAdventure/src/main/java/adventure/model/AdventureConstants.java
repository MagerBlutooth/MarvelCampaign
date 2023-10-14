package adventure.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdventureConstants {

    public static final int STARTING_CARDS = 40;
    public static final int STARTING_CAPTAINS = 4;
    public static final int NUMBER_OF_WORLDS = 8;
    public static final int SECTIONS_PER_WORLD = 4;
    public static final int SECTION_CLEAR_GOAL = 3;
    public static final int NUM_DRAFT_CARDS = 3;
    public static final int MAX_STATIONS = 4;
    public static final String BOSS_FILE = "data/bossList.csv";
    public static final String SECTION_FILE = "data/sectionsList.csv";
    public static final String PROFILE_1 = "profiles/adventureProfile1.txt";
    public static final String PROFILE_2 = "profiles/adventureProfile2.txt";
    public static final String PROFILE_3 = "profiles/adventureProfile3.txt";

    public static final int MOOK_BASE_HP = 1;

    public static final List<Integer> TEAM_MEMBER_START_CHOICES = Arrays.asList(30, 40, 50, 100);

    public static final List<Integer> TEAM_CAPTAIN_START_CHOICES = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

}
