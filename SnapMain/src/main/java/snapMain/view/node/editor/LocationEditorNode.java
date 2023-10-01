package snapMain.view.node.editor;

import snapMain.controller.MainDatabase;
import snapMain.controller.editor.LocationEditorNodeController;
import snapMain.model.thing.Location;
import snapMain.view.node.CampaignNode;

public class LocationEditorNode extends CampaignNode {

    LocationEditorNodeController controller;
    public LocationEditorNode()
    {
        fxmlCampaignGrabber.grabFXML("locationEditorNode.fxml", this);
        controller = fxmlCampaignGrabber.getController();
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
