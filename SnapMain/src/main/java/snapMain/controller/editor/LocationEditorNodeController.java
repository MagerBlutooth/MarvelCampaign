package snapMain.controller.editor;

import snapMain.controller.MainDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import snapMain.model.target.Location;

public class LocationEditorNodeController extends BasicNodeController<MainDatabase, Location> {

    @FXML
    TextField nameField;

    @FXML
    TextArea effectField;
    MainDatabase database;
    Location location;

    @Override
    public void initialize(MainDatabase d, Location l) {
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
