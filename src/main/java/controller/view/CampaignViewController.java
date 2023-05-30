package controller.view;

import model.thing.Thing;
import view.IconImage;
import view.ViewSize;


public abstract class CampaignViewController<T extends Thing> {

    public abstract void initialize(T t, ViewSize v);

    public abstract void setMainImage(IconImage mainImage, ViewSize v);

}
