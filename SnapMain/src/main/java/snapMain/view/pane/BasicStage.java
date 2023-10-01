package snapMain.view.pane;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BasicStage extends Stage{

    private double xOffset;
    private double yOffset;

    public BasicStage() {
        getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
        initStyle(StageStyle.UNDECORATED);

    }

    public void initialize(Parent root)
    {
        setRoot(root);
        makeDraggable(root);
        centerWindow();
    }

    private void centerWindow() {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        setX((primScreenBounds.getWidth() - getWidth()/2));
        setY((primScreenBounds.getHeight() - getHeight()/2));
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
