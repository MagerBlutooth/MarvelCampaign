package view.main;

import javafx.application.Application;
import javafx.stage.Stage;
import model.logger.MHandler;
import model.logger.MLogger;
import view.pane.PlayerMainScreen;

public class PlayerApplication extends Application {

    private final static MLogger logger = new MLogger(PlayerApplication.class);

    @Override
    public void start(Stage stage) {

        MHandler handler = new MHandler();
        MLogger.LOGGER.addHandler(handler);
        logger.info("Initializing Logger...");
        new PlayerMainScreen();
        stage.close();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}