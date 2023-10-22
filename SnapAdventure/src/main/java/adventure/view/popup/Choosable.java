package adventure.view.popup;

import javafx.scene.control.DialogPane;
import snapMain.model.target.SnapTarget;

public interface Choosable<T extends SnapTarget> {

    public void setChoice(T t);

}
