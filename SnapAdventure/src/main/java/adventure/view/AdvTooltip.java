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

    private final BooleanProperty isHoveringPrimary = new SimpleBooleanProperty(false);

    public AdvTooltip()
    {
        setShowDelay(Duration.seconds(1));
        setHideDelay(Duration.seconds(0.1));
        super.setAutoHide(false);
        setFont(Font.font("Ubuntu", 15));
        setStyle("-fx-text-fill: white;");
        setId("info");
    }

    public void initialize(Node node) {
        isHoveringTargetPrimary(node);
    }

    public BooleanProperty isHoveringPrimaryProperty()
    {
        return isHoveringPrimary;
    }

    public void isHoveringTargetPrimary(Node node){
        node.setOnMouseEntered(e -> isHoveringPrimary.set(true));
        node.setOnMouseExited(e -> isHoveringPrimary.set(false));
    }

    @Override
    public void hide() {
        if(isHoveringPrimaryProperty().get())
        {
            AdvTooltip.super.show();
        }
        else
        {
            AdvTooltip.super.hide();
        }
    }
}
