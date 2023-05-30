package view.node.editor;

import controller.ControllerDatabase;
import controller.editor.LocationEditorNodeController;
import model.thing.Location;
import view.node.CampaignNode;

public class LocationEditorNode extends CampaignNode {

    LocationEditorNodeController controller;
    public LocationEditorNode()
    {
        fxmlGrabber.grabFXML("locationEditorNode.fxml", this);
        controller = fxmlGrabber.getController();
    }

    public void initialize(ControllerDatabase d, Location w)
    {
        getController().initialize(d, w);
    }

    public Location generateLocation()
    {
        return controller.generateLocation();
    }

}
