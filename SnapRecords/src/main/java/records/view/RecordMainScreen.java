package records.view;

import snapMain.controller.MainDatabase;
import snapMain.view.SplashScreen;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import snapMain.model.database.MasterThingDatabase;
import snapMain.view.GameStage;
import records.controller.HallOfFameManagerController;
import records.view.fxml.FXMLRecordGrabber;

import static snapMain.view.main.GameApplication.isSplashLoaded;

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
            FXMLRecordGrabber grabber = new FXMLRecordGrabber();
            grabber.grabFXML("hallOfFameManager.fxml", hallOfFameManagerPane);
            HallOfFameManagerController hallOfFameManagerController = grabber.getController();
            hallOfFameManagerController.initialize(new MainDatabase(db));
        });
    }
}
