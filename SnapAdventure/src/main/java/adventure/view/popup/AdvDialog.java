package adventure.view.popup;

import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class AdvDialog<T extends Object> extends Dialog<T> {

    private double xOffset;
    private double yOffset;

    public AdvDialog()
    {
        initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
    }

    protected void initialize(Window ownerWindow)
    {
        initOwner(ownerWindow);
    }
}
