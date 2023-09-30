package adventure.view;

import adventure.model.AdvMainDatabase;
import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.model.database.MasterThingDatabase;
import campaign.view.GameStage;
import campaign.view.SplashScreen;
import campaign.view.fxml.FXMLCampaignGrabber;
import campaign.view.fxml.FXMLGrabber;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import adventure.controller.AdvMainMenuController;
import adventure.view.pane.AdvMainMenuPane;

import static adventure.view.GameMain.isSplashLoaded;

public class GameMainScreen extends GameStage {
    AdvMainMenuController controller;

    AdvMainMenuPane mainPane;

    public GameMainScreen() {
        mainPane = new AdvMainMenuPane();
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("mainMenu.fxml", mainPane);
        controller = fxmlAdventureGrabber.getController();
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
            db.loadDatabase();
            fadeOut.play();
            FXMLAdventureGrabber grabber = new FXMLAdventureGrabber();
            grabber.grabFXML("mainMenu.fxml", mainPane);
            AdvMainMenuController mainMenuController = grabber.getController();
            AdvMainDatabase mainDatabase = new AdvMainDatabase(db);
            mainMenuController.initialize(mainDatabase);
        });
    }

    public AdvMainMenuController getController()
    {
        return controller;
    }
}
