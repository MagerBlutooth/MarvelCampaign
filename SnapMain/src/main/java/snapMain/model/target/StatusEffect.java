package snapMain.model.target;

public enum StatusEffect {
    PIG(18), RAPTOR(26), NONE(-1);

    final int id;
    StatusEffect(int i)
    {
        id = i;
    }
}
