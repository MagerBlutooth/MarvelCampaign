package adventure.controller;

import adventure.model.AdvMainDatabase;
import adventure.model.target.base.AdvLocation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import snapMain.controller.editor.BasicNodeController;
import snapMain.model.target.Location;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.thing.LocationView;

public class SectionEditorNodeController extends BasicNodeController<AdvMainDatabase, Location> {

    @FXML
    LocationView imageView;
    @FXML
    TextArea effectField;
    AdvMainDatabase database;
    Location location;

    public void initialize(AdvMainDatabase d, AdvLocation s) {
        database = d;
        location = s.getLocation();
        effectField.setText(s.getEffect());
        setImages();
    }

    private void setImages() {
        IconImage i = database.grabImage(location);
        imageView.setImage(i, ViewSize.GIANT);
    }

    public AdvLocation generateSection()
    {
        AdvLocation s = new AdvLocation(location);
        s.setEffect(effectField.getText());
        return s;
    }
}
