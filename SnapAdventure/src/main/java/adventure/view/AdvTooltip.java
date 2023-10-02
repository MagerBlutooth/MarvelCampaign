package adventure.view;

import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public class AdvTooltip extends Tooltip {

    public AdvTooltip()
    {
        setShowDelay(Duration.seconds(0.1));
        setStyle("-fx-text-fill: white;");
        setId("info");
    }
}
