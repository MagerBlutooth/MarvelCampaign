package campaign.view.button;

import campaign.controller.ButtonToolBarController;
import javafx.scene.layout.HBox;
import campaign.view.fxml.FXMLCampaignGrabber;
import campaign.view.pane.BasicPane;

public class ButtonToolBar extends HBox {

    ButtonToolBarController controller;

    public ButtonToolBar()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("buttonToolBar.fxml", this);
        controller = fxmlCampaignGrabber.getController();
    }

    public void initialize(BasicPane backPane)
    {
        controller.initialize(backPane);
    }
}
