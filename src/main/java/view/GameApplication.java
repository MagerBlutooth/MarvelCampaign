package view;

import javafx.application.Application;
import javafx.application.Preloader;
import javafx.stage.Stage;
import model.logger.MHandler;
import model.logger.MLogger;
import view.pane.MainScreen;

public class GameApplication extends Application {

    private final static MLogger logger = new MLogger(GameApplication.class);
    public static boolean isSplashLoaded = false;

    @Override
    public void start(Stage stage) {

        MHandler handler = new MHandler();
        MLogger.LOGGER.addHandler(handler);
        logger.info("Initializing Logger...");
        new MainScreen();
        stage.close();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}