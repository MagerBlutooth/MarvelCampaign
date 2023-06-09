package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;
import view.pane.CampaignStage;

import java.util.Objects;

public class GameStage extends CampaignStage {

    private double xOffset;
    private double yOffset;

    public GameStage() {
        getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png"))));
        initStyle(StageStyle.UNDECORATED);
    }

    public void initialize(Parent root)
    {
        setRoot(root);
        makeDraggable(root);
    }

    private void setRoot(Parent root)
    {
        Scene scene = new Scene(root);
        setScene(scene);
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
