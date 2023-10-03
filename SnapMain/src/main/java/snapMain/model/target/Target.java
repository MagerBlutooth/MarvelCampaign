package snapMain.model.target;

public interface Target {
    TargetType getTargetType();
    int getID();

    void setEnabled(boolean enabled);

    boolean isEnabled();

    String getName();

    void setID(int id);
}
