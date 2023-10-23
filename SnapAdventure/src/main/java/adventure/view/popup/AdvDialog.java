package adventure.view.popup;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Dialog;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class AdvDialog<T extends Object> extends Dialog<T> {

    public AdvDialog()
    {
        initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
    }

    public void centerToParent(Window window) {
        double x = window.getX() + window.getWidth()/4;
        double y = window.getY() + window.getHeight()/3;
        this.setX(x);
        this.setY(y);
    }
}
