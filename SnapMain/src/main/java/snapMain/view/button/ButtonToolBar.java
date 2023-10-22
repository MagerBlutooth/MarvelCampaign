package snapMain.view.button;

import snapMain.controller.ButtonToolBarController;
import javafx.scene.layout.HBox;
import snapMain.view.fxml.FXMLMainGrabber;
import snapMain.view.pane.FullViewPane;

import java.util.logging.FileHandler;

public class ButtonToolBar extends HBox {

    ButtonToolBarController controller;

    public ButtonToolBar()
    {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("buttonToolBar.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(FullViewPane backPane)
    {
        controller.initialize(backPane);
    }

    public void initialize(FullViewPane backPane, FileHandler fileHandler)
    {
        controller.initialize(backPane, fileHandler);
    }

    public void removeBackButton() {
        controller.removeBackButton();
    }
}
