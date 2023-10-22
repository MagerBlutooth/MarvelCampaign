package adventure.view.node;

import adventure.model.AdvMainDatabase;
import adventure.model.Team;
import adventure.model.target.base.AdvToken;
import adventure.model.target.InfinityStoneID;
import adventure.view.AdvTooltip;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import snapMain.model.database.TargetDatabase;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InfinityStoneDisplayNode extends HBox {

    ConcurrentHashMap<InfinityStoneID, ControlNode<AdvToken>> infinityStoneMap = new ConcurrentHashMap<>();
    Team team;

    public void initialize(AdvMainDatabase mainDatabase, Team t)
    {
        team = t;
        TargetDatabase<AdvToken> tokens = mainDatabase.getAdvTokens();
        for(InfinityStoneID id: InfinityStoneID.values())
        {
            AdvToken token = tokens.lookup(id.getID());
            ControlNode<AdvToken> stoneNode = new ControlNode<>();
            stoneNode.initialize(mainDatabase, token, mainDatabase.grabImage(token), ViewSize.TINY, false);
            infinityStoneMap.put(id, stoneNode);
            this.getChildren().add(stoneNode);
        }
        refresh();
    }

    private void showTooltip(Node n, String effect) {
        AdvTooltip tooltip = new AdvTooltip();
        tooltip.setText(effect);
        Tooltip.install(n, tooltip);
    }

    public void refresh() {
        for (Map.Entry<InfinityStoneID, ControlNode<AdvToken>> entry : infinityStoneMap.entrySet()) {
            InfinityStoneID stoneID = entry.getKey();
            if (team.hasInfinityStone(stoneID)) {
                highlight(stoneID, entry.getValue());
            } else {
                lowlight(entry.getValue());
            }
        }
    }

    private void lowlight(ControlNode<AdvToken> node) {
        node.lowlight();
        ColorAdjust disableColor = new ColorAdjust();
        disableColor.setSaturation(-1);
        node.setEffect(disableColor);
        node.setOnMouseEntered(null);
    }

    private void highlight(InfinityStoneID id, ControlNode<AdvToken> node) {
        node.highlight();
        node.setEffect(null);
        node.setOnMouseEntered(e -> showTooltip(node, id.getEffect()));
    }

}
