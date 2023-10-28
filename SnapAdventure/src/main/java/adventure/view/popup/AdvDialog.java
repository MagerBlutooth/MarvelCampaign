package adventure.view.popup;

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

    protected void initialize(Parent root)
    {
        centerToParent(root.getScene().getWindow());
        makeDraggable(root);
    }

    protected void centerToParent(Window window) {
        double x = window.getX() + window.getWidth()/4;
        double y = window.getY() + window.getHeight()/3;
        this.setX(x);
        this.setY(y);
    }

    private void makeDraggable(Parent root) {
        root.setOnMousePressed(event -> {
            xOffset = this.getX() - event.getScreenX();
            yOffset = this.getY() - event.getScreenY();
        });
        root.setOnMouseDragged(event -> {
            setX(event.getScreenX() + xOffset);
            setY(event.getScreenY() + yOffset);
        });
    }
}
