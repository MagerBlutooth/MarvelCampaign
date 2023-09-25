package adventure.view;

import adventure.view.fxml.FXMLAdventureGrabber;
import campaign.model.database.MasterThingDatabase;
import campaign.view.GameStage;
import campaign.view.SplashScreen;
import campaign.view.fxml.FXMLCampaignGrabber;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import adventure.controller.AdventureMainMenuController;
import adventure.view.pane.AdventureMainMenuPane;

import static adventure.view.GameMain.isSplashLoaded;

public class GameMainScreen extends GameStage {
    AdventureMainMenuController controller;

    AdventureMainMenuPane mainPane;

    public GameMainScreen() {
        mainPane = new AdventureMainMenuPane();
        FXMLAdventureGrabber fxmlAdventureGrabber = new FXMLAdventureGrabber();
        fxmlAdventureGrabber.grabFXML("adventureMainMenu.fxml", mainPane);
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
            FXMLCampaignGrabber grabber = new FXMLCampaignGrabber();
            grabber.grabFXML("adventureMainMenu.fxml", mainPane);
            AdventureMainMenuController mainMenuController = grabber.getController();
            mainMenuController.initialize(db);
        });
    }

    public AdventureMainMenuController getController()
    {
        return controller;
    }
}
