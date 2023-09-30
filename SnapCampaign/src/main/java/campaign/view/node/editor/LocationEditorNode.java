package campaign.view.node.editor;

import campaign.controller.MainDatabase;
import campaign.controller.editor.LocationEditorNodeController;
import campaign.model.thing.Location;
import campaign.view.node.CampaignNode;

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
