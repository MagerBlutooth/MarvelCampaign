package snapMain.view.grabber;

import javafx.scene.image.Image;
import snapMain.view.IconImage;

import java.net.URL;

public class ImageGrabber extends FileGrabber {

    private final String newIconFile = "/newIcon.png";

    public ImageGrabber() {
        super();
    }

    public IconImage grabCostImage() {
        String costLabelFile = "/costIcon.png";
        URL url = getClass().getResource(costLabelFile);
        assert url != null;
        return new IconImage(url.toExternalForm());
    }

    public IconImage grabPowerImage()
    {
        String powerLabelFile = "/powerIcon.png";
        URL url = getClass().getResource(powerLabelFile);
        assert url != null;
        return new IconImage(url.toExternalForm());
    }

    public IconImage grabStarImage()
    {
        String starImageFile = "/captainStar.png";
        URL url = getClass().getResource(starImageFile);
        assert url != null;
        return new IconImage(url.toExternalForm());
    }

    public IconImage grabHealImage() {
        String starImageFile = "/healSign.png";
        URL url = getClass().getResource(starImageFile);
        assert url != null;
        return new IconImage(url.toExternalForm());
    }

    public IconImage grabDefectImage() {
        String starImageFile = "/defectSign.png";
        URL url = getClass().getResource(starImageFile);
        assert url != null;
        return new IconImage(url.toExternalForm());
    }

    public IconImage grabBossImage() {
        String bossImageFile = "/bossImage.png";
        URL url = getClass().getResource(bossImageFile);
        assert url != null;
        return new IconImage(url.toExternalForm());
    }

    public IconImage grabWoundImage() {
        String starImageFile = "/woundSign.png";
        URL url = getClass().getResource(starImageFile);
        assert url != null;
        return new IconImage(url.toExternalForm());
    }

    public IconImage grabEliminateImage() {
        String starImageFile = "/eliminateSign.png";
        URL url = getClass().getResource(starImageFile);
        assert url != null;
        return new IconImage(url.toExternalForm());
    }

    public IconImage grabMIAImage() {
        String starImageFile = "/sendAwayIcon.png";
        URL url = getClass().getResource(starImageFile);
        assert url != null;
        return new IconImage(url.toExternalForm());
    }

    public IconImage grabCaptureImage() {
        String starImageFile = "/captureIcon.png";
        URL url = getClass().getResource(starImageFile);
        assert url != null;
        return new IconImage(url.toExternalForm());
    }

    public IconImage grabToTempImage() {
        String starImageFile = "/toTempIcon.png";
        URL url = getClass().getResource(starImageFile);
        assert url != null;
        return new IconImage(url.toExternalForm());
    }

    public IconImage grabToTeamImage() {
        String starImageFile = "/toTeamIcon.png";
        URL url = getClass().getResource(starImageFile);
        assert url != null;
        return new IconImage(url.toExternalForm());
    }
}
