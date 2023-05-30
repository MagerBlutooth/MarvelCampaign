package model.thing;

public abstract class EffectThing extends Thing {

    String effect;

    public EffectThing()
    {
        effect = "";
    }

    public String getEffect()
    {
        return effect;
    }

    public void setEffect(String e)
    {
        effect = e;
    }
}
