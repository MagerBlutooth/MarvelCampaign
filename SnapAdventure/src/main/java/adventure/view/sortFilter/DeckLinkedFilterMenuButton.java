package adventure.view.sortFilter;

import adventure.controller.DeckGridController;
import adventure.model.target.ActiveCard;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import snapMain.controller.grid.GridDisplayController;

public class DeckLinkedFilterMenuButton extends MenuButton {

    public void initialize(GridDisplayController<ActiveCard> controller, DeckGridController deckController)
    {
        for(String f: controller.getFilterOptionKeys()) {
            CheckMenuItem checkBox = new CheckMenuItem(f);
            checkBox.setOnAction(actionEvent -> {
                controller.filter(checkBox.getText(), !checkBox.isSelected());
                deckController.toggleNodeLights();
            });
            getItems().add(checkBox);
        }
    }
}
