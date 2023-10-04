package snapMain.model.target;

public interface SnapTarget {
    TargetType getTargetType();
    int getID();

    void setEnabled(boolean enabled);

    boolean isEnabled();

    String getName();

    void setID(int id);

    boolean hasAttribute(String entry);
}
