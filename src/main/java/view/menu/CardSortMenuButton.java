package view.menu;

import controller.grid.GridDisplayController;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import model.thing.Card;

import java.util.ArrayList;
import java.util.List;

public class CardSortMenuButton extends MenuButton {

    List<MenuItem> menuItems = new ArrayList<>();

    public void initialize(GridDisplayController<Card> controller) {

        for (String c : controller.getSortOptions()) {
                MenuItem m = new MenuItem(c);
                m.setOnAction(actionEvent -> {
                    controller.sort(c);
                });
                menuItems.add(m);
            }
            getItems().addAll(menuItems);
        }
}

