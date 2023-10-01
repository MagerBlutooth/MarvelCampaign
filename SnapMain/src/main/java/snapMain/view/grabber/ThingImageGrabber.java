package snapMain.view.grabber;

import snapMain.model.helper.FileHelper;
import snapMain.model.thing.TargetType;
import snapMain.view.IconImage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static java.nio.file.Files.copy;

public class ThingImageGrabber extends ImageGrabber {

    public ThingImageGrabber(TargetType t) {
        super();
        File folderLoc = new File(getImageFolder());
        if (!folderLoc.exists())
            folderLoc.mkdir();
        fileTypeName = FileHelper.convertToFolderName(t.toString());
        File imageFolderLoc = new File(getImageTypeFolder());
        if(!imageFolderLoc.exists())
            imageFolderLoc.mkdir();
    }

    public IconImage grabImage(int id) {
        String imagePath = getImagePath(id);
        File file = new File(imagePath);
        if (checkForImageFile(imagePath)) {
            try {
                URL url = file.toURI().toURL();
                return new IconImage(url.toExternalForm());
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    private String getImagePath(int id)  {
        String imageName = FileHelper.convertToPNGName(id + "");
        return getImageTypeFolder() + File.separator + imageName;
    }

    private String getImageTypeFolder() {
        return getImageFolder() + File.separator + fileTypeName;
    }

    public boolean checkForImageFile(String name) {
        File file = new File(name);
        return file.exists();
    }

    public void saveImage(IconImage image, int id) {
        try {
            String existingPath = new URL(image.getPath()).getPath();
            existingPath = existingPath.replaceFirst("/", "");
            Path newPath = Paths.get(getImagePath(id));
            copy(Paths.get(existingPath), newPath, StandardCopyOption.REPLACE_EXISTING);
            logger.info("Saved new image at %", newPath);
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.error("Error: Could not copy image file for image %", id);
        }
    }
}
