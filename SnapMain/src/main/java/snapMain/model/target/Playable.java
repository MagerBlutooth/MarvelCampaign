package snapMain.model.target;

public interface Playable extends Target{

    public String[] toSaveStringArray();

    public void fromSaveStringArray(String[] mInfo);
}
