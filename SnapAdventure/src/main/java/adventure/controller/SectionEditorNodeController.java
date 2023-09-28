package adventure.controller;

import adventure.model.AdvControllerDatabase;
import adventure.model.Section;
import campaign.controller.editor.BasicNodeController;
import campaign.model.thing.Location;
import campaign.model.thing.ThingType;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.thing.LocationView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SectionEditorNodeController extends BasicNodeController<AdvControllerDatabase, Location> {

    @FXML
    LocationView imageView;
    @FXML
    Label nameLabel;
    @FXML
    TextArea effectField;
    AdvControllerDatabase database;
    Location location;

    public void initialize(AdvControllerDatabase d, Section s) {
        database = d;
        location = s.getLocation();
        nameLabel.setText(s.getName());
        effectField.setText(s.getEffect());
        setImages();
    }

    private void setImages() {
        IconImage i = database.grabImage(location, ThingType.LOCATION);
        imageView.setImage(i, ViewSize.LARGE);
    }

    public Section generateSection()
    {
        Section s = new Section(location);
        s.setEffect(effectField.getText());
        return s;
    }
}
