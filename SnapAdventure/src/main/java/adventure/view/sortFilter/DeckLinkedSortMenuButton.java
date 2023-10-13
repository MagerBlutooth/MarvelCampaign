package adventure.view.sortFilter;

import adventure.controller.DeckGridController;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import snapMain.controller.grid.GridDisplayController;
import snapMain.model.sortFilter.CardSortMode;
import snapMain.model.target.Card;
import snapMain.model.target.CardList;

import java.util.ArrayList;
import java.util.List;

public class DeckLinkedSortMenuButton extends MenuButton {

    List<MenuItem> menuItems = new ArrayList<>();

    public void initialize(GridDisplayController<Card> gridList, DeckGridController deckController)
    {
        for (String c : gridList.getSortOptions()) {
            MenuItem m = new MenuItem(c);
            m.setOnAction(actionEvent -> {
                gridList.sort(c);
                deckController.toggleNodeLights();
            });
            menuItems.add(m);
        }
        getItems().addAll(menuItems);

    }
}
