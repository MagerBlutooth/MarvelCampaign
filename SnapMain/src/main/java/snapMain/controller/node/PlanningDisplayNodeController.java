package snapMain.controller.node;

import snapMain.controller.MainDatabase;
import snapMain.controller.grid.BaseGridActionController;
import snapMain.model.thing.Card;
import snapMain.model.thing.Faction;
import snapMain.model.thing.PlanningInfo;
import snapMain.model.thing.TargetType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import snapMain.model.database.MasterThingDatabase;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.PlanningSheet;

public class PlanningDisplayNodeController {

    public Label influenceAmount;
    MainDatabase mainDatabase;
    MasterThingDatabase masterThingDatabase;
    @FXML
    GridDisplayNode<Card> agentDisplay;
    @FXML
    TextArea passwordArea;
    @FXML
    PlanningSheet planningSheet;

    public void initialize(MainDatabase d, Faction f) {
        mainDatabase = d;
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
        baseGridActionController.initialize(mainDatabase);
        agentDisplay.initialize(planningInfo.getActiveAgents(), TargetType.CARD, baseGridActionController, ViewSize.SMALL, false);
        influenceAmount.setText(String.valueOf(planningInfo.getInfluence()));
    }
}
