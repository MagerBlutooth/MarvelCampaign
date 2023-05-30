package view.pane;

import controller.MainMenuController;
import javafx.animation.FadeTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model.database.MasterThingDatabase;
import view.GameStage;
import view.SplashScreen;
import view.fxml.FXMLGrabber;

import java.util.concurrent.atomic.AtomicReference;

import static view.GameApplication.isSplashLoaded;

public class MainScreen extends GameStage {

    MainMenuController controller;

    MainMenuPane mainPane;

    public MainScreen() {
        mainPane = new MainMenuPane();
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("mainMenu.fxml", mainPane);
        controller = fxmlGrabber.getController();
        if(!isSplashLoaded)
            loadSplashScreen();
        initialize(mainPane);
        show();
    }

    public void loadSplashScreen()
    {
        MasterThingDatabase db = new MasterThingDatabase();
        SplashScreen splash = new SplashScreen();
        mainPane.getChildren().setAll(splash);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), splash);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), splash);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);

        fadeIn.play();

        fadeIn.setOnFinished((e) -> {
            db.loadDatabase();
            fadeOut.play();
            FXMLGrabber grabber = new FXMLGrabber();
            grabber.grabFXML("mainMenu.fxml", mainPane);
            MainMenuController mainMenuController = grabber.getController();
            mainMenuController.initialize(db);
        });
    }

    public MainMenuController getController() {
        return controller;
    }
}
