package view.pane;

import controller.PlayerMainMenuController;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import model.database.MasterThingDatabase;
import view.GameStage;
import view.SplashScreen;
import view.fxml.FXMLGrabber;

import static view.main.GameApplication.isSplashLoaded;

public class PlayerMainScreen extends GameStage {

    PlayerMainMenuController controller;
    PlayerMainMenuPane mainPane;

    public PlayerMainScreen() {
        mainPane = new PlayerMainMenuPane();
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("playerMainMenu.fxml", mainPane);
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
            grabber.grabFXML("playerMainMenu.fxml", mainPane);
            PlayerMainMenuController mainMenuController = grabber.getController();
            mainMenuController.initialize(db);
        });
    }

    public PlayerMainMenuController getController() {
        return controller;
    }
}
