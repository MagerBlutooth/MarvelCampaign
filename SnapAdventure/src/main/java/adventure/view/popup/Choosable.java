package adventure.view.popup;

import snapMain.model.target.SnapTarget;

public interface Choosable<T extends SnapTarget> {

    public void setChoice(T t);
    public void enableOKButton();

}
