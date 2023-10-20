package snapMain.model.target;

import snapMain.model.constants.SnapMainConstants;

public abstract class EffectBaseObject extends BaseObject {

    String effect;

    public EffectBaseObject()
    {
        super();
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
