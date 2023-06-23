package view.button;

import controller.ButtonToolBarController;
import javafx.scene.layout.HBox;
import view.fxml.FXMLGrabber;
import view.pane.CampaignPane;

public class ButtonToolBar extends HBox {

    ButtonToolBarController controller;

    public ButtonToolBar()
    {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("buttonToolBar.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(CampaignPane backPane)
    {
        controller.initialize(backPane);
    }
}
