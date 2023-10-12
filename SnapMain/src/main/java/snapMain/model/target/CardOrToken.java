package snapMain.model.target;

public class CardOrToken implements Playable {

    Playable p;
    public CardOrToken()
    {
        p = new Card();
    }
    @Override
    public String[] toSaveStringArray() {
        return p.toSaveStringArray();
    }

    @Override
    public void fromSaveStringArray(String[] mInfo) {
        p.fromSaveStringArray(mInfo);
    }

    @Override
    public String getEffect() {
        return "";
    }

    @Override
    public TargetType getTargetType() {
        return p.getTargetType();
    }

    @Override
    public int getID() {
        return p.getID();
    }

    @Override
    public void setEnabled(boolean enabled) {
        p.setEnabled(enabled);
    }

    @Override
    public boolean isEnabled() {
        return p.isEnabled();
    }

    @Override
    public String getName() {
        return p.getName();
    }

    @Override
    public void setID(int id) {
        p.setID(id);
    }

    @Override
    public boolean hasAttribute(String entry) {
        return p.hasAttribute(entry);
    }
}
