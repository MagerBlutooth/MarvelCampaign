package campaign.view.fxml;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public abstract class FXMLGrabber {

    protected FXMLLoader loader;
    public abstract String getFXMLFolder();
     public FXMLLoader grabFXML(String fxmlName, Parent root) {
        String resourcePath = getFXMLFolder() + fxmlName;
        URL location = getClass().getResource(resourcePath);
        loader = new FXMLLoader(location);
        loader.setRoot(root);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loader;
    }

    public FXMLLoader grabFXML(String fxmlName, Parent root, Object controller) {
        String resourcePath = getFXMLFolder() + fxmlName;
        URL location = root.getClass().getResource(resourcePath);
        loader = new FXMLLoader(location);
        loader.setRoot(root);
        loader.setController(controller);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loader;
    }
    public <R extends Object> R getController() {
        return loader.getController();
    }

}
