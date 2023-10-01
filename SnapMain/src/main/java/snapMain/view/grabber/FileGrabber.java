package snapMain.view.grabber;

import snapMain.model.logger.MLogger;

import java.net.URL;

public class FileGrabber {

    MLogger logger = new MLogger(FileGrabber.class);

    private final String dataFolder;
    private final String imageFolder;
    private final String musicFolder;
    String fileTypeName;

    public FileGrabber()
    {
        String baseDataPath = "data";
        dataFolder = baseDataPath;
        String baseImagePath = "images";
        imageFolder = baseImagePath;
        String baseMusicPath = "music";
        musicFolder = baseMusicPath;
    }

    String getImageFolder() {
        return imageFolder;
    }

    String getMusicFolder() {
        return musicFolder;
    }

    String getDefaultMusic() {
        String defaultMusic = "/defaultMusic.mp3";
        URL url = getClass().getResource(defaultMusic);
        assert url != null;
        return url.toExternalForm();
    }

    protected String getDataFolder() {
        return dataFolder;
    }

    String getMusicCachePath() {
        String basicMusicCachePath = "musicCache";
        return basicMusicCachePath;
    }

}
