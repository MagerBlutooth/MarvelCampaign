package snapMain.view;

import javafx.scene.image.Image;

//Custom image class that allows the path to be retrieved. Path must be stored in URL format.
public class IconImage extends Image {

    String path;

    public IconImage(String p) {
        super(p);
        path = p;
    }

    public String getPath() {
        return path;
    }
}
