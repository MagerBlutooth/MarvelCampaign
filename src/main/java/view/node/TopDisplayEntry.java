package view.node;

import controller.ControllerDatabase;
import controller.grid.TopDisplayNodeController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import model.database.ThingDatabase;
import model.thing.HallOfFameEntry;
import view.IconImage;
import view.ViewSize;
import view.fxml.FXMLGrabber;
import view.thing.CardView;

public class TopDisplayEntry extends StackPane {

    @FXML
    Label rank;
    @FXML
    CardView image;

    public TopDisplayEntry()
    {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("topDisplayEntry.fxml", this, this);
    }

    public void initialize(int r, IconImage i, int c)
    {
        rank.setText(String.valueOf(r));
        image.setImage(i, ViewSize.TINY);
    }
}
