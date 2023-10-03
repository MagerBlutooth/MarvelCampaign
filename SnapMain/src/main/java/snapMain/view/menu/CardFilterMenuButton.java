package snapMain.view.menu;

import snapMain.controller.grid.GridDisplayController;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import snapMain.model.target.Card;

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
