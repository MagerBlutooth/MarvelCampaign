package adventure.model;

import adventure.model.target.*;
import adventure.model.target.base.AdvCard;
import adventure.model.target.base.AdvLocationList;
import adventure.model.target.base.Mook;
import snapMain.model.database.TargetDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class FinalWorld extends World {

    public FinalWorld(AdventureDatabase db, int wNum,ActiveCardList freeAgents, AdvLocationList locations) {
        super(db);
        worldNum = wNum;
        section1 = new Section(db, 1, locations.get(0) , new Enemy(new Mook(),
                bonusCalculator.calculateMook(worldNum)));
        initializeMiniBoss(section1, freeAgents);
        section2 = new Section(db, 2, locations.get(1), new Enemy(new Mook(),
                bonusCalculator.calculateMook(worldNum)));
        initializeMiniBoss(section2, freeAgents);
        section3 = new Section(db,3, locations.get(2), new Enemy(new Mook(),
                bonusCalculator.calculateMook(worldNum)));
        initializeMiniBoss(section3, freeAgents);
        section4 = new Section(db,4, locations.get(3), new Enemy(new Mook(),
                bonusCalculator.calculateMook(worldNum)));
        initializeMiniBoss(section4, freeAgents);
        bossSection = new BossSection(db, new Enemy(new Mook()));
        initialize(freeAgents);
        section1.reveal();
        currentSectionNum = 1;

    }
    public void initializeMiniBoss(Section s, ActiveCardList freeAgents) {
        ActiveCardList agentsCopy = new ActiveCardList(new ArrayList<>());
        agentsCopy = agentsCopy.cloneNewList(freeAgents.getThings());
        Collections.shuffle(agentsCopy.getThings());
        TargetDatabase<AdvCard> bosses = database.getAdvCards();
        ActiveCard card = agentsCopy.get(0);
        AdvCard boss = bosses.lookup(card.getID());
        if(boss != null) {
            Enemy enemy = new Enemy(boss, worldNum);
            s.setEnemy(enemy);
            freeAgents.remove(card);
        }
    }
}
