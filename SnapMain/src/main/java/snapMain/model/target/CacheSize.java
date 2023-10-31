package snapMain.model.target;

public enum CacheSize {
    SMALL(8,4), MEDIUM(10,8), LARGE(12,6), MASSIVE(16,4), GALACTIC (20,2);

    final int prevalence;
    final int cubeCount;
    CacheSize(int cubeCount, int prevalence)
    {
        this.cubeCount = cubeCount;
        this.prevalence = prevalence;
    }

    public int getPrevalence()
    {
        return prevalence;
    }

    public int getCubeCount()
    {
        return cubeCount;
    }
}
