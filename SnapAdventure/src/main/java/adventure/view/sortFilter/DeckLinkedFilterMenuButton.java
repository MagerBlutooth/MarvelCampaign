package adventure.view.sortFilter;

import adventure.controller.DeckGridController;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import snapMain.controller.grid.GridDisplayController;
import snapMain.model.target.Card;

import java.util.ArrayList;
import java.util.List;

public class DeckLinkedFilterMenuButton extends MenuButton {

    public void initialize(GridDisplayController<Card> controller, DeckGridController deckController)
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
