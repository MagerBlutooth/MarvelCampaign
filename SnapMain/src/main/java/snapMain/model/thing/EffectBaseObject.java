package snapMain.model.thing;

public abstract class EffectBaseObject extends BaseObject {

    String effect;

    public EffectBaseObject()
    {
        effect = "";
    }

    public EffectBaseObject(EffectBaseObject t)
    {
        super(t);
        effect = t.getEffect();
    }

    public String getEffect()
    {
        return effect;
    }

    public void setEffect(String e)
    {
        effect = e;
    }

    public String getSortableName() {
        return getName().toLowerCase();
    }
}
