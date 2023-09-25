package campaign.view.main;

import campaign.view.pane.PlayerMainScreen;
import javafx.application.Application;
import javafx.stage.Stage;
import campaign.model.logger.MHandler;
import campaign.model.logger.MLogger;

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