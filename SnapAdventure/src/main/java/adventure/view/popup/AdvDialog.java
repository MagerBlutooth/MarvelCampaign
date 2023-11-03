package adventure.view.popup;

import javafx.scene.control.Dialog;
import javafx.stage.Modality;
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
