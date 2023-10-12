package adventure.view;

import adventure.view.node.EnemyControlNode;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

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
