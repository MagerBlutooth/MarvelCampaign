package snapMain.view.pane;

import snapMain.controller.PlayerMainMenuController;
import snapMain.view.GameStage;
import snapMain.view.SplashScreen;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import snapMain.model.database.MasterThingDatabase;
import snapMain.view.fxml.FXMLMainGrabber;

import static snapMain.view.main.GameApplication.isSplashLoaded;

public class PlayerMainScreen extends GameStage {

    PlayerMainMenuController controller;
    PlayerMainMenuPane mainPane;

    public PlayerMainScreen() {
        mainPane = new PlayerMainMenuPane();
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("playerMainMenu.fxml", mainPane, getClass());
        controller = fxmlMainGrabber.getController();
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
            fadeOut.play();
            db.loadDatabase();
            FXMLMainGrabber grabber = new FXMLMainGrabber();
            grabber.grabFXML("playerMainMenu.fxml", mainPane);
            PlayerMainMenuController mainMenuController = grabber.getController();
            mainMenuController.initialize(db);
        });
    }

    public PlayerMainMenuController getController() {
        return controller;
    }
}
