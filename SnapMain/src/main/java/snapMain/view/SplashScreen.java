package snapMain.view;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import snapMain.view.fxml.FXMLMainGrabber;

public class SplashScreen extends StackPane {

    @FXML
    Label loadLabel;

    FadeTransition fadeTransition;

    public SplashScreen()
    {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("splashScreen.fxml", this, this);
    }

    public void initialize()
    {
        fadeTransition = new FadeTransition(Duration.seconds(1), loadLabel);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.setDelay(Duration.seconds(1.0));
    }

    public void playTextTransition()
    {
        fadeTransition.play();
    }

}
