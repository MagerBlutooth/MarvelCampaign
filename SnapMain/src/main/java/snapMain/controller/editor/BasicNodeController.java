package snapMain.controller.editor;

import snapMain.controller.MainDatabase;
import snapMain.model.target.BaseObject;

public abstract class BasicNodeController<C extends MainDatabase, T extends BaseObject>{

    C database;
    BaseObject subject;

    public void initialize(C db, T t) {
        database = db;
        subject = t;
    }
}
