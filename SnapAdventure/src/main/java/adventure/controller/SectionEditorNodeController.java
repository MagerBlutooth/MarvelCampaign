package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.thing.AdvLocation;
import snapMain.controller.editor.BasicNodeController;
import snapMain.model.target.Location;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.thing.LocationView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SectionEditorNodeController extends BasicNodeController<AdvMainDatabase, Location> {

    @FXML
    LocationView imageView;
    @FXML
    Label nameLabel;
    @FXML
    TextArea effectField;
    AdvMainDatabase database;
    Location location;

    public void initialize(AdvMainDatabase d, AdvLocation s) {
        database = d;
        location = s.getLocation();
        nameLabel.setText(s.getName());
        effectField.setText(s.getEffect());
        setImages();
    }

    private void setImages() {
        IconImage i = database.grabImage(location, TargetType.LOCATION);
        imageView.setImage(i, ViewSize.LARGE);
    }

    public AdvLocation generateSection()
    {
        AdvLocation s = new AdvLocation(location);
        s.setEffect(effectField.getText());
        return s;
    }
}
