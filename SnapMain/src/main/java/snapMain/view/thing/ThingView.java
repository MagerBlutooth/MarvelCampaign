package snapMain.view.thing;

import snapMain.controller.view.CampaignViewController;
import javafx.application.Platform;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import snapMain.model.target.BaseObject;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.fxml.FXMLCampaignGrabber;

import java.io.File;
import java.net.MalformedURLException;

public class ThingView<T extends BaseObject> extends StackPane {
    IconImage image;
    protected FXMLCampaignGrabber fxmlCampaignGrabber;
    boolean editable;
    ViewSize viewSize = ViewSize.SMALL;

    public ThingView()
    {
        fxmlCampaignGrabber = new FXMLCampaignGrabber();
    }

    public <V extends CampaignViewController<T>> V getController()
    {
        return fxmlCampaignGrabber.getController();
    }

    public void addDragAndDrop()
    {
        setOnDragOver(this::mouseDragOver);
        setOnDragDropped(this::mouseDragDropped);
        setOnDragExited((this::mouseDragExited));
    }
    public void setImage(IconImage i, ViewSize v)
    {
        image = i;
        getController().setMainImage(image, v);

    }

    private void mouseDragDropped(final DragEvent e) {
        final Dragboard db = e.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            success = true;
            // Only get the first file from the list
            final File file = db.getFiles().get(0);
            Platform.runLater(() -> {
                try {
                    image = new IconImage(file.toURI().toURL().toString());
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                }
                setImage(image, viewSize);
            });
        }
        e.setDropCompleted(success);
        e.consume();
    }

    private void mouseDragExited(final DragEvent e) {
        setStyle(null);
    }

    private void mouseDragOver(final DragEvent e) {
        final Dragboard db = e.getDragboard();

        final boolean isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith(".png")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpeg")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpg");

        if (db.hasFiles()) {
            if (isAccepted) {
                setStyle("-fx-border-color:dodgerblue;"
                        + "-fx-border-width: 5;"
                        + "-fx-border-style: solid;");
                e.acceptTransferModes(TransferMode.COPY);
            }
        } else {
            e.consume();
        }
    }

    public void setViewSize(ViewSize s) {
        viewSize = s;
    }
}
