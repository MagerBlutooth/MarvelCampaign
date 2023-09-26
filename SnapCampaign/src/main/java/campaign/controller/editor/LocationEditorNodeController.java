package campaign.controller.editor;

import campaign.controller.ControllerDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import campaign.model.thing.Location;

public class LocationEditorNodeController extends BasicNodeController<Location> {

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
