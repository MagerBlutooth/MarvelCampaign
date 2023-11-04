package adventure.view;

import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class AdvTooltip extends Tooltip {

    public AdvTooltip()
    {
        setShowDelay(Duration.seconds(1));
        setHideDelay(Duration.seconds(0.1));
        super.setAutoHide(false);
        setFont(Font.font("Ubuntu", 15));
        setStyle("-fx-text-fill: white;");
        setId("info");
    }
}
