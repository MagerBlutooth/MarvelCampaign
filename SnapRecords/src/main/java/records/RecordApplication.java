package records;

import javafx.application.Application;
import javafx.stage.Stage;
import campaign.model.logger.MHandler;
import campaign.model.logger.MLogger;
import records.view.RecordMainScreen;

public class RecordApplication extends Application {

    private final static MLogger logger = new MLogger(RecordApplication.class);

    @Override
    public void start(Stage stage) {

        MHandler handler = new MHandler();
        MLogger.LOGGER.addHandler(handler);
        logger.info("Initializing Logger...");
        new RecordMainScreen();
        stage.close();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}