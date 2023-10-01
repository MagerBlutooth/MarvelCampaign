package snapMain.controller.view;

import snapMain.model.thing.BaseObject;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;


public abstract class CampaignViewController<T extends BaseObject> {

    public abstract void initialize(T t, ViewSize v);

    public abstract void setMainImage(IconImage mainImage, ViewSize v);

}
