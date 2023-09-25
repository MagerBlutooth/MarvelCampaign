package campaign.controller.view;

import campaign.model.thing.Thing;
import campaign.view.IconImage;
import campaign.view.ViewSize;


public abstract class CampaignViewController<T extends Thing> {

    public abstract void initialize(T t, ViewSize v);

    public abstract void setMainImage(IconImage mainImage, ViewSize v);

}
