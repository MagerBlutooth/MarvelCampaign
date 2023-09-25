package campaign.view.main;

import campaign.view.pane.MainScreen;
import javafx.application.Application;
import javafx.stage.Stage;
import campaign.model.logger.MHandler;
import campaign.model.logger.MLogger;

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