package snapMain.view;

import snapMain.view.pane.BasicStage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;

import java.util.Objects;

public class GameStage extends BasicStage {

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
        if(root.getScene() != null)
            setScene(root.getScene());
        else
            setScene(new Scene(root));
    }

}
