package snapMain.view.pane;

import snapMain.controller.EditMenuController;
import snapMain.controller.MainDatabase;
import snapMain.view.SplashScreen;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import snapMain.model.database.MasterThingDatabase;
import snapMain.view.GameStage;
import snapMain.view.fxml.FXMLMainGrabber;

import static snapMain.view.main.GameApplication.isSplashLoaded;

public class MainScreen extends GameStage {

    EditMenuController controller;

    MainMenuPane mainPane;

    public MainScreen() {
        mainPane = new MainMenuPane();
        if(!isSplashLoaded)
            loadSplashScreen();
        initialize(mainPane);
        show();
    }

    public void loadSplashScreen()
    {
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
            MasterThingDatabase db = new MasterThingDatabase();
            MainDatabase mainDatabase = new MainDatabase(db);
            db.loadDatabase();
            FXMLMainGrabber grabber = new FXMLMainGrabber();
            grabber.grabFXML("editorMenu.fxml", mainPane);
            controller = grabber.getController();
            controller.initialize(mainDatabase);
        });
    }

    public EditMenuController getController() {
        return controller;
    }
}
