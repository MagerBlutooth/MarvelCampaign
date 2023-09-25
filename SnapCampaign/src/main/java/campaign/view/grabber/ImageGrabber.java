package campaign.view.grabber;

import campaign.view.IconImage;

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

    public IconImage grabHPImage()
    {
        String hpLabelFile = "/heartIcon.png";
        URL url = getClass().getResource(hpLabelFile);
        assert url != null;
        return new IconImage(url.toExternalForm());
    }

    public IconImage grabCoinImage()
    {
        String coinImageFile = "/coinIcon.png";
        URL url = getClass().getResource(coinImageFile);
        assert url != null;
        return new IconImage(url.toExternalForm());
    }

    public IconImage grabDeckImage()
    {
        String deckImageFile = "/deckIcon.png";
        URL url = getClass().getResource(deckImageFile);
        assert url != null;
        return new IconImage(url.toExternalForm());
    }
    public IconImage grabGraveImage()
    {
        String graveImageFile = "/graveIcon.png";
        URL url = getClass().getResource(graveImageFile);
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
}
