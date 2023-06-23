package controller.node;

import controller.ControllerDatabase;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import model.thing.Campaign;
import model.thing.Card;
import model.thing.EffectThing;
import model.thing.Faction;
import view.ViewSize;
import view.node.control.ControlNode;

import java.util.List;
import java.util.Random;

public class RandomizerNodeController {

    @FXML
    Text randomText;
    @FXML
    StackPane displayPaneLeft;
    @FXML
    StackPane displayPaneMiddle;
    @FXML
    StackPane displayPaneRight;
    @FXML
    Label shieldLabel;

    @FXML
    Label hydraLabel;

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
    @FXML
    public void flipCoin()
    {
        Random random = new Random();
        int coin = random.nextInt(2);
        if(coin == 0)
            randomText.setText("Heads");
        else
            randomText.setText("Tails");
    }
    @FXML
    public void rollDice()
    {
        Random random = new Random();
        int dice = random.nextInt(6);
        randomText.setText(String.valueOf(dice));
    }

    @FXML
    public void generateMercs()
    {
        List<Card> mercs = campaign.randomMercs();
        ControlNode<EffectThing> viewNodeLeft = createCardView(mercs.get(0));
        ControlNode<EffectThing> viewNodeMiddle = createCardView(mercs.get(1));
        ControlNode<EffectThing> viewNodeRight = createCardView(mercs.get(2));
        displayPaneLeft.getChildren().clear();
        displayPaneRight.getChildren().clear();
        displayPaneMiddle.getChildren().clear();
        effectText.setText("");
        displayPaneLeft.getChildren().add(viewNodeLeft);
        displayPaneMiddle.getChildren().add(viewNodeMiddle);
        displayPaneRight.getChildren().add(viewNodeRight);
    }

    public void initialize(ControllerDatabase cd, Campaign c) {

        campaign = c;
        controllerDatabase = cd;
        shield = campaign.getShield();
        hydra = campaign.getHydra();
        shieldLabel.setText(shield.getName());
        hydraLabel.setText(hydra.getName());
    }

    public ControlNode<EffectThing> createCardView(EffectThing e)
    {
        ControlNode<EffectThing> viewNode = new ControlNode<>();
        viewNode.setMaxWidth(300.0);
        viewNode.initialize(controllerDatabase, e,controllerDatabase.grabImage(e, e.getThingType()), ViewSize.LARGE, true);
        viewNode.setBorder(new Border(new BorderStroke(campaign.getColor(e), BorderStrokeStyle.SOLID, new CornerRadii(5.0), new BorderWidths(3.0))));
        return viewNode;
    }

    public void setDisplay(EffectThing t)
    {
        effectText.setText("");
        displayPaneLeft.getChildren().clear();
        displayPaneRight.getChildren().clear();
        displayPaneMiddle.getChildren().clear();
        displayPaneMiddle.getChildren().add(createCardView(t));
        effectText.setText(t.getEffect());
        displayPaneMiddle.setAlignment(Pos.CENTER);
    }
}
