package adventure.model;

public enum AdvProfile {
    profile1(AdventureConstants.PROFILE_1, AdventureConstants.LOG_1),
    profile2(AdventureConstants.PROFILE_2, AdventureConstants.LOG_2),
    profile3(AdventureConstants.PROFILE_3, AdventureConstants.LOG_3);

    final String profileFile;
    final String logFile;

    public String getProfileFile()
    {
        return profileFile;
    }

    public String getLogFile()
    {
        return logFile;
    }
    AdvProfile(String profile, String log)
    {
        profileFile = profile;
        logFile = log;
    }
}
