package snapMain.view.button;

import snapMain.controller.ButtonToolBarController;
import javafx.scene.layout.HBox;
import snapMain.view.fxml.FXMLCampaignGrabber;
import snapMain.view.pane.FullViewPane;

public class ButtonToolBar extends HBox {

    ButtonToolBarController controller;

    public ButtonToolBar()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("buttonToolBar.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(FullViewPane backPane)
    {
        controller.initialize(backPane);
    }
}
