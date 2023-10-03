package snapMain.controller.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.Location;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.grabber.ThingImageGrabber;
import snapMain.view.thing.LocationView;


public class LocationViewController extends CampaignViewController<Location> {

    @FXML
    LocationView locationView;

    @FXML
    ImageView mainImage;
    int imageSize;
    private ThingImageGrabber imageGrabber;
    Location location;
    ContextMenu contextMenu = new ContextMenu();

    @Override
    public void initialize(Location l, ViewSize s) {
        location = l;
        imageGrabber = new ThingImageGrabber(TargetType.LOCATION);
        imageSize = s.getSizeVal();
        locationView.setViewSize(s);
        setMainImage(imageGrabber.grabImage(l.getID()), s);
        setTooltip();
    }
    @Override
    public void setMainImage(IconImage i, ViewSize v) {
        mainImage.setImage(i);
        mainImage.setFitWidth(v.getSizeVal());
        mainImage.setFitHeight(v.getSizeVal());
    }


    public IconImage getImage() {
        return locationView.getImage();
    }

    public void setContextMenu(TargetDatabase<Location> w) {
        contextMenu = new ContextMenu();
        locationView.setOnContextMenuRequested(e -> contextMenu.show(locationView, e.getScreenX(), e.getScreenY()));
    }

    private void setTooltip() {
        Tooltip cardToolTip = new Tooltip(location.getName() + "\n" + location.getEffect());
        cardToolTip.setFont(new Font("Ubuntu", 20));
        locationView.setOnMouseEntered(e -> {
            Node node = (Node) e.getSource();
            cardToolTip.show(node, e.getScreenX() + 50, e.getScreenY());
        });
        locationView.setOnMouseExited(e -> cardToolTip.hide());
    }
}
