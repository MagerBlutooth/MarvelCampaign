package view.button;

import controller.ButtonToolBarController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
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
