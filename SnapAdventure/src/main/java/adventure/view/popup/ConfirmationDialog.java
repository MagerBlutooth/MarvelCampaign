package adventure.view.popup;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.util.Objects;

public class ConfirmationDialog extends Alert {

    public ConfirmationDialog(String dialogText)
    {
        super(AlertType.CONFIRMATION, dialogText, ButtonType.YES, ButtonType.NO);
        getDialogPane().getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/css_adventure/mainStyle.css")).toExternalForm());
        getDialogPane().getStyleClass().add("myDialog");
        setHeaderText(null);
        setGraphic(null);
        initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
    }


}
