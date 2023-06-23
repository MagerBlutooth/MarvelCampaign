package controller.node;

import controller.ControllerDatabase;
import controller.grid.BaseGridActionController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.database.MasterThingDatabase;
import model.thing.*;
import view.ViewSize;
import view.node.GridDisplayNode;
import view.node.PlanningSheet;

import java.util.ArrayList;

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
        masterThingDatabase = d.getMasterThingDatabase();
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
