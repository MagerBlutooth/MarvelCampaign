package model.thing;

public abstract class EffectThing extends Thing {

    String effect;

    public EffectThing()
    {
        effect = "";
    }

    public EffectThing(EffectThing t)
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
