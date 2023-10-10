package adventure.controller;

import adventure.model.thing.Enemy;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class HPDisplayNodeController {
    @FXML
    Label healthValue;
    @FXML
    ProgressBar healthBar;

    IntegerProperty hpIntegerProperty;

    Enemy boss;

    public void initialize(Enemy b) {
        boss = b;
        healthValue.setText(b.getID()+"");
        healthBar.setProgress(1.0);
        hpIntegerProperty = new SimpleIntegerProperty();
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
        hpIntegerProperty.set(boss.getCurrentHP());

    }

    public IntegerProperty getHPProperty() {
        return hpIntegerProperty;
    }
}
