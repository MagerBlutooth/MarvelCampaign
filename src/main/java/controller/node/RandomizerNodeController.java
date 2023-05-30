package controller.node;

import controller.ControllerDatabase;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.thing.Campaign;
import model.thing.EffectThing;
import model.thing.Faction;
import model.thing.Thing;
import view.ViewSize;
import view.node.control.ControlNode;

public class RandomizerNodeController {

    @FXML
    Label shieldLabel;

    @FXML
    Label hydraLabel;

    @FXML
    StackPane displayPane;

    @FXML
    Text effectText;

    Campaign campaign;
    ControllerDatabase controllerDatabase;
    Faction shield;
    Faction hydra;

    @FXML
    public void randomShieldAgent()
    {
        setDisplay(campaign.randomShieldAgent());
    }
    @FXML
    public void randomHydraAgent()
    {
        setDisplay(campaign.randomHydraAgent());
    }
    @FXML
    public void randomShieldLocation()
    {
        setDisplay(campaign.randomShieldLocation());
    }
    @FXML
    public void randomHydraLocation()
    {
        setDisplay(campaign.randomHydraLocation());
    }
    @FXML
    public void randomAnyAgent()
    {
        setDisplay(campaign.randomAgent());
    }
    @FXML
    public void randomAnyLocation()
    {
        setDisplay(campaign.randomLocation());
    }
    @FXML
    public void randomFreeAgent()
    {
        setDisplay(campaign.randomFreeAgent());
    }
    @FXML
    public void randomFreeLocation()
    {
        setDisplay(campaign.randomFreeLocation());
    }

    public void initialize(ControllerDatabase cd, Campaign c) {

        campaign = c;
        controllerDatabase = cd;
        shield = campaign.getShield();
        hydra = campaign.getHydra();
        shieldLabel.setText(shield.getName());
        hydraLabel.setText(hydra.getName());
    }

    public void setDisplay(EffectThing t)
    {
        effectText.setText("");
        displayPane.getChildren().clear();
        ControlNode<EffectThing> viewNode = new ControlNode<>();
        viewNode.setMaxWidth(300.0);
        viewNode.initialize(controllerDatabase, t,controllerDatabase.grabImage(t, t.getThingType()), ViewSize.LARGE, true);
        viewNode.setBorder(new Border(new BorderStroke(campaign.getColor(t), BorderStrokeStyle.SOLID, new CornerRadii(5.0), new BorderWidths(3.0))));
        displayPane.getChildren().add(viewNode);
        effectText.setText(t.getEffect());
        displayPane.setAlignment(Pos.CENTER);
    }
}
