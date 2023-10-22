package adventure.view.popup;

import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class AdvDialog<T extends Object> extends Dialog<T> {

    public AdvDialog()
    {
        initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
    }
}
