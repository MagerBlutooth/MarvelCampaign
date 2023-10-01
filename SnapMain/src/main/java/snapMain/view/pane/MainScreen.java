package snapMain.view.pane;

import snapMain.controller.MainMenuController;
import snapMain.view.SplashScreen;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import snapMain.model.database.MasterThingDatabase;
import snapMain.view.GameStage;
import snapMain.view.fxml.FXMLCampaignGrabber;

import static snapMain.view.main.GameApplication.isSplashLoaded;

public class MainScreen extends GameStage {

    MainMenuController controller;

    MainMenuPane mainPane;

    public MainScreen() {
        mainPane = new MainMenuPane();
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("mainMenu.fxml", mainPane);
        controller = fxmlCampaignGrabber.getController();
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
            grabber.grabFXML("mainMenu.fxml", mainPane);
            MainMenuController mainMenuController = grabber.getController();
            mainMenuController.initialize(db);
        });
    }

    public MainMenuController getController() {
        return controller;
    }
}
