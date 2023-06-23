package view.pane;

import controller.ControllerDatabase;
import controller.grid.HallOfFameManagerController;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import model.database.MasterThingDatabase;
import view.GameStage;
import view.SplashScreen;
import view.fxml.FXMLGrabber;
import view.pane.manager.HallOfFameManagerPane;

import static view.main.GameApplication.isSplashLoaded;

public class RecordMainScreen extends GameStage {

    HallOfFameManagerPane hallOfFameManagerPane;

    public RecordMainScreen() {
        hallOfFameManagerPane = new HallOfFameManagerPane();
        if(!isSplashLoaded)
            loadSplashScreen();
        initialize(hallOfFameManagerPane);
        show();
    }

    public void loadSplashScreen()
    {
        MasterThingDatabase db = new MasterThingDatabase();
        SplashScreen splash = new SplashScreen();
        hallOfFameManagerPane.getChildren().setAll(splash);

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
            grabber.grabFXML("hallOfFameManager.fxml", hallOfFameManagerPane);
            HallOfFameManagerController hallOfFameManagerController = grabber.getController();
            hallOfFameManagerController.initialize(new ControllerDatabase(db));
        });
    }
}
