package snapMain.model.target;

public interface Playable extends SnapTarget {

    public String[] toSaveStringArray();

    public void fromSaveStringArray(String[] mInfo);
}
