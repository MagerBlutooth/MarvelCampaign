package adventure.controller;

import adventure.view.node.DiceNode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import snapMain.controller.MainDatabase;
import snapMain.model.logger.MLogger;
import snapMain.view.grabber.IconConstant;

import java.util.Random;

public class DiceNodeController {

    @FXML
    Button d2Button;
    @FXML
    Button d4Button;
    @FXML
    Button d6Button;
    @FXML
    Button d8Button;
    @FXML
    Button d10Button;
    @FXML
    Button d12Button;
    @FXML
    Button d20Button;
    @FXML
    Button d100Button;
    @FXML
    Label dieResult;

    MLogger logger = new MLogger(DiceNodeController.class);

    public void rolld2() {rollDie(2); }
    public void rolld4()
    {
        rollDie(4);
    }

    public void rolld6()
    {
        rollDie(6);
    }

    public void rolld8()
    {
        rollDie(8);
    }

    public void rolld10()
    {
        rollDie(10);
    }
    public void rolld12()
    {
        rollDie(12);
    }

    public void rolld20()
    {
        rollDie(20);
    }
    public void rolld100()
    {
        rollDie(100);
    }

    public void initialize(MainDatabase mainDatabase)
    {
        setGraphic(d2Button, new ImageView(mainDatabase.grabIcon(IconConstant.D2)), false);
        setGraphic(d4Button, new ImageView(mainDatabase.grabIcon(IconConstant.D4)), true);
        setGraphic(d6Button, new ImageView(mainDatabase.grabIcon(IconConstant.D6)), false);
        setGraphic(d8Button, new ImageView(mainDatabase.grabIcon(IconConstant.D8)), false);
        setGraphic(d10Button, new ImageView(mainDatabase.grabIcon(IconConstant.D10)), false);
        setGraphic(d12Button, new ImageView(mainDatabase.grabIcon(IconConstant.D12)), false);
        setGraphic(d20Button, new ImageView(mainDatabase.grabIcon(IconConstant.D20)), false);
        setGraphic(d100Button, new ImageView(mainDatabase.grabIcon(IconConstant.D100)), false);
    }

    private void setGraphic(Button b, ImageView image, boolean triangle)
    {
        if(triangle)
        {
            image.setFitWidth(35);
            image.setFitHeight(35);
        }
        else
        {
            image.setFitWidth(30);
            image.setFitHeight(30);
        }
        b.setGraphic(image);
    }


    private void rollDie(int i) {
        Random random = new Random();
        int result = random.nextInt(i)+1;
        dieResult.setText(result+"");
        logger.info("Rolled d"+i+": "+result);
    }

    public void refresh() {
        dieResult.setText("");
    }
}
