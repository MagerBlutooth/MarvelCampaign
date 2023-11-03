package adventure.view.sortFilter;

import adventure.controller.DeckGridController;
import adventure.model.AdventureConstants;
import adventure.model.target.ActiveCard;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
        getItems().add(getTempOption(controller, deckController));
    }

    private MenuItem getTempOption(GridDisplayController<ActiveCard> controller, DeckGridController deckController) {
        CheckMenuItem m = new CheckMenuItem("Temp");
        m.setOnAction(actionEvent -> {
            controller.filter(AdventureConstants.TEMP_FILTER_STRING, !m.isSelected());
            deckController.toggleNodeLights();
        });
        return m;
    }
}
