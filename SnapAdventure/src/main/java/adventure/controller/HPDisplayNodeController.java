package adventure.controller;

import adventure.model.thing.Boss;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class HPDisplayNodeController {
    @FXML
    Label healthValue;
    @FXML
    ProgressBar healthBar;

    Boss boss;

    public void initialize(Boss b) {
        boss = b;
        healthValue.setText(b.getID()+"");
        healthBar.setProgress(1.0);
        refresh();
    }

    public void refresh()
    {
        double percentage = (1.0* boss.getCurrentHP() / boss.getBaseHP());
        if(percentage > 1)
            healthBar.setStyle("-fx-accent: gold;");
        else
            healthBar.setStyle("-fx-accent: red;");
        healthValue.setText(boss.getCurrentHP()+"");
        healthBar.setProgress(percentage);
    }

}
