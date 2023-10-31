package snapMain.view.node.editor;

import snapMain.controller.MainDatabase;
import snapMain.controller.editor.LocationEditorNodeController;
import snapMain.model.target.Location;
import snapMain.view.node.CampaignNode;

public class LocationEditorNode extends CampaignNode {

    LocationEditorNodeController controller;
    public LocationEditorNode()
    {
        fxmlMainGrabber.grabFXML("locationEditorNode.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(MainDatabase d, Location w)
    {
        getController().initialize(d, w);
    }

    public Location generateLocation()
    {
        return controller.generateLocation();
    }

}
