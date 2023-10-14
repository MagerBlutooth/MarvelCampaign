package adventure.model;

public enum Difficulty {
    EASY(2), NORMAL(3), HARD(4);

    final int requiredSectionsToClear;
    Difficulty(int secClear)
    {

        this.requiredSectionsToClear = secClear;
    }

    public int getSectionsRequiredToClear() {
        return requiredSectionsToClear;
    }
}
