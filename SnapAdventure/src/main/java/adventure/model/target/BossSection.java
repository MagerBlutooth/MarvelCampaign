package adventure.model.target;

import adventure.model.AdventureDatabase;
import adventure.model.target.base.Ruins;

public class BossSection extends Section {

    public BossSection(AdventureDatabase db, Enemy e){
        super(db, 5, new Ruins(), e);
    }

    public void setEnemy(Enemy e) {
        enemy = e;
    }
}
