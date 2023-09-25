package campaign.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.util.Duration;
import campaign.model.logger.MLogger;

public class ScrollSetup {

    MLogger logger = new MLogger(ScrollSetup.class);
    private Timeline scrolltimeline = new Timeline();
    private double scrollVelocity = 0;

    //Higher speed value = slower scroll.
    int speed = 50;
    boolean valid = false;
    public void setupScrolling(ScrollPane scrollPane) {
        scrolltimeline.setCycleCount(Timeline.INDEFINITE);
        scrolltimeline.getKeyFrames().add(new KeyFrame(Duration.millis(20), (ActionEvent) -> { dragScroll(scrollPane);}));

        scrollPane.setOnDragExited((DragEvent event) -> {
            if (event.getY() > 0) {
                scrollVelocity = 1.0 / speed;
            }
            else {
                scrollVelocity = -1.0 / speed;
            }
            if (valid && !event.isDropCompleted() && event.getX() > 0 && event.getX() < scrollPane.getWidth()){
                scrolltimeline.play();
            }
        });

        scrollPane.setOnDragEntered(event -> {
            scrolltimeline.stop();
            valid = true;
        });
        scrollPane.setOnDragOver((DragEvent event) ->{
            event.acceptTransferModes(TransferMode.MOVE);
        });
        scrollPane.setOnDragDropped((DragEvent event) ->{
            valid = false;
        });
        scrollPane.setOnScroll((ScrollEvent event)-> {
            scrolltimeline.stop();
        });
    }
    private void dragScroll(ScrollPane sp) {
        ScrollBar sb = getVerticalScrollbar(sp);
        if (sb != null) {
            double newValue = sb.getValue() + scrollVelocity;
            newValue = Math.min(newValue, 1.0);
            newValue = Math.max(newValue, 0.0);
            sb.setValue(newValue);
        }
    }

    private ScrollBar getVerticalScrollbar(ScrollPane sp) {
        ScrollBar result = null;
        for (Node n : sp.lookupAll(".scroll-bar")) {
            if (n instanceof ScrollBar) {
                ScrollBar bar = (ScrollBar) n;
                if (bar.getOrientation().equals(Orientation.VERTICAL)) {
                    result = bar;
                }
            }
        }
        return result;
    }
}
