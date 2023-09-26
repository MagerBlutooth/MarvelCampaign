package campaign.controller.node;

import campaign.controller.ControllerDatabase;
import campaign.controller.grid.BaseGridActionController;
import campaign.model.thing.Card;
import campaign.model.thing.Faction;
import campaign.model.thing.PlanningInfo;
import campaign.model.thing.ThingType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import campaign.model.database.MasterThingDatabase;
import campaign.view.ViewSize;
import campaign.view.node.GridDisplayNode;
import campaign.view.node.PlanningSheet;

public class PlanningDisplayNodeController {

    public Label influenceAmount;
    ControllerDatabase controllerDatabase;
    MasterThingDatabase masterThingDatabase;
    @FXML
    GridDisplayNode<Card> agentDisplay;
    @FXML
    TextArea passwordArea;
    @FXML
    PlanningSheet planningSheet;

    public void initialize(ControllerDatabase d, Faction f) {
        controllerDatabase = d;
        masterThingDatabase = d.getAdvMasterThingDatabase();
        passwordArea.setText("");
        planningSheet.initialize(f.getFactionLabel());
    }

    public void initializeAgentDisplay()
    {
        String password = passwordArea.getText().trim();
        PlanningInfo planningInfo = new PlanningInfo();
        planningInfo.fromSaveString(password, masterThingDatabase.getCards());
        BaseGridActionController<Card> baseGridActionController = new BaseGridActionController<>();
        baseGridActionController.initialize(controllerDatabase);
        agentDisplay.initialize(planningInfo.getActiveAgents(), ThingType.CARD, baseGridActionController, ViewSize.SMALL, false);
        influenceAmount.setText(String.valueOf(planningInfo.getInfluence()));
    }
}
