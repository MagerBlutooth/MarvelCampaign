package controller.node;

import controller.ControllerDatabase;
import controller.grid.AgentGridActionController;
import controller.grid.BaseGridActionController;
import controller.grid.ThingActionController;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import model.database.MasterThingDatabase;
import model.thing.Card;
import model.thing.CardList;
import model.thing.Faction;
import model.thing.ThingType;
import view.ViewSize;
import view.node.GridDisplayNode;
import view.node.PlanningSheet;
import view.node.control.ControlNode;

import java.util.ArrayList;

public class PlanningDisplayNodeController {

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
        CardList cardList = new CardList(new ArrayList<>());
        cardList.fromString(password, masterThingDatabase.getCards());
        BaseGridActionController<Card> baseGridActionController = new BaseGridActionController<>();
        baseGridActionController.initialize(controllerDatabase);
        agentDisplay.initialize(cardList, ThingType.CARD, baseGridActionController, ViewSize.SMALL, false);
    }
}
