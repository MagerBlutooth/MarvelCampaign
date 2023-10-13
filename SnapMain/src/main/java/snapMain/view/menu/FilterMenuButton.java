package snapMain.view.menu;

import snapMain.controller.grid.GridDisplayController;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import snapMain.model.target.SnapTarget;

public class FilterMenuButton<T extends SnapTarget> extends MenuButton {

    public void initialize(GridDisplayController<T> controller)
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
