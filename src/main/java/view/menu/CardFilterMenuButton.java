package view.menu;

import controller.grid.GridDisplayController;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import model.thing.Card;

public class CardFilterMenuButton extends MenuButton {

    public void initialize(GridDisplayController<Card> controller)
    {
        for(String f: controller.getFilterOptionKeys()) {
                CheckMenuItem checkBox = new CheckMenuItem(f);
                checkBox.setOnAction(actionEvent -> {
                    controller.filter(checkBox.getText(), !checkBox.isSelected());
                });
                getItems().add(checkBox);
            }
        }
}
