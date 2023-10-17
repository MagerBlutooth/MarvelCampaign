package adventure.view.node;

import adventure.model.AdvMainDatabase;
import adventure.model.Team;
import adventure.model.thing.InfinityStoneID;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.HBox;
import snapMain.controller.MainDatabase;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.Token;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InfinityStoneDisplayNode extends HBox {

    ConcurrentHashMap<InfinityStoneID, ControlNode<Token>> infinityStoneMap = new ConcurrentHashMap<>();
    Team team;

    public void initialize(MainDatabase mainDatabase, Team t)
    {
        team = t;
        TargetDatabase<Token> tokens = mainDatabase.getTokens();
        for(InfinityStoneID id: InfinityStoneID.values())
        {
            Token token = tokens.lookup(id.getID());
            ControlNode<Token> stoneNode = new ControlNode<>();
            stoneNode.initialize(mainDatabase, token, mainDatabase.grabImage(token), ViewSize.TINY, false);
            infinityStoneMap.put(id, stoneNode);
            this.getChildren().add(stoneNode);
        }
        refresh();
    }

    public void refresh() {
        for (Map.Entry<InfinityStoneID, ControlNode<Token>> entry : infinityStoneMap.entrySet()) {
            InfinityStoneID stoneID = entry.getKey();
            if (team.hasInfinityStone(stoneID)) {
                entry.getValue().highlight();
                entry.getValue().setEffect(null);
            } else {
                entry.getValue().lowlight();
                ColorAdjust disableColor = new ColorAdjust();
                disableColor.setSaturation(-1);
                entry.getValue().setEffect(disableColor);
            }
        }
    }

}
