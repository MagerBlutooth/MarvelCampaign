package adventure.model;

public class EnemyHPCalculator {

    public int calculateBoss(int worldNum)
    {
        worldNum = Math.max(0, worldNum);
        if(worldNum < 3)
            return 2;
        if(worldNum < 5)
            return 3;
        if(worldNum < 7)
            return 5;
        if(worldNum < 8)
            return 6;
        if(worldNum < 9)
            return 8;
        return 10;
    }

    public int calculateMook(int worldNum)
    {
        worldNum = Math.max(0, worldNum);
        if(worldNum < 3)
            return AdventureConstants.MOOK_BASE_HP;
        if(worldNum < 5) //2 HP Starting in World 3
            return AdventureConstants.MOOK_BASE_HP + 1;
        if(worldNum < 7) //3 HP Starting in World 5
            return AdventureConstants.MOOK_BASE_HP + 2;
        if(worldNum < 9) //4 HP Starting in World 7
            return AdventureConstants.MOOK_BASE_HP + 3;
        //5 HP in secret bonus world
        return AdventureConstants.MOOK_BASE_HP + 4;
    }
}
