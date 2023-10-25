package snapMain.view.menu;

import snapMain.controller.grid.GridDisplayController;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import snapMain.model.target.Card;
import snapMain.model.target.SnapTarget;

import java.util.ArrayList;
import java.util.List;

public class SortMenuButton<T extends SnapTarget> extends MenuButton {

    List<MenuItem> menuItems = new ArrayList<>();

    public void initialize(GridDisplayController<T> controller) {

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

