package snapMain.model.target;

public class CardOrToken implements SnapTarget {

    Unit u;

    public CardOrToken()
    {
        u = new Card();
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.CARD_OR_TOKEN;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void setEnabled(boolean enabled) {

    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setID(int id) {

    }

    @Override
    public boolean hasAttribute(String entry) {
        return false;
    }
}
