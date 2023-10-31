package adventure.view;

import adventure.model.AdventureConstants;
import javafx.application.Application;
import javafx.stage.Stage;
import snapMain.model.logger.MHandler;
import snapMain.model.logger.MLogger;


public class GameMain extends Application {

    private final static MLogger logger = new MLogger(GameMain.class);
    public static boolean isSplashLoaded = false;
    @Override
    public void start(Stage stage) {

        MHandler handler = new MHandler();
        MLogger.LOGGER.addHandler(handler);
        logger.info("Initializing Logger...");
        new GameMainScreen();
        stage.close();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}