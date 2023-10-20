package snapMain.model.target;

import snapMain.model.database.TargetDatabase;

public interface Playable<T extends SnapTarget> extends SnapTarget {

    public String toSaveString();
    public void fromSaveString(String mInfo, TargetDatabase<T> things);
    public String getEffect();
}
