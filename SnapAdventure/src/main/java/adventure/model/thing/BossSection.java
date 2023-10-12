package adventure.model.thing;

import adventure.model.AdvMainDatabase;
import adventure.model.AdventureConstants;
import adventure.model.AdventureDatabase;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.PlayableDatabase;

public class BossSection extends Section {

    public BossSection(AdventureDatabase db, Enemy e){
        super(db, 0, new Ruins(), e);
    }

    public BossSection(BossSection b)
    {
        super(b);
    }

    public void setEnemy(Enemy e) {
        enemy = e;
    }
}
