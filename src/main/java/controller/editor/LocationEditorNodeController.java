package controller.editor;

import controller.ControllerDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.thing.Location;

public class LocationEditorNodeController extends CampaignNodeController<Location> {

    @FXML
    TextField nameField;

    @FXML
    TextArea effectField;
    ControllerDatabase database;
    Location location;

    @Override
    public void initialize(ControllerDatabase d, Location l) {
        database = d;
        location = l;
        nameField.setText(l.getName());
        effectField.setText(l.getEffect());
    }

    public Location generateLocation() {
        location.setName(nameField.getText());
        location.setEffect(effectField.getText());
        return location;
    }
}
