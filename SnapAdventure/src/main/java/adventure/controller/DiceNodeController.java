package adventure.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Random;

public class DiceNodeController {

    @FXML
    Button d4Button;

    @FXML
    Button d6Button;

    @FXML
    Button d8Button;

    @FXML
    Button d12Button;
    @FXML
    Button d20Button;
    @FXML
    Button d100Button;
    @FXML
    Label dieResult;

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


    private void rollDie(int i) {
        Random random = new Random();
        int result = random.nextInt(i);
        dieResult.setText(result+1+"");
    }
}
