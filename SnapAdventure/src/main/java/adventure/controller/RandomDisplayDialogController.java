package adventure.controller;

import adventure.view.popup.Choosable;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import snapMain.controller.MainDatabase;
import snapMain.model.target.SnapTarget;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;

public class RandomDisplayDialogController<T extends SnapTarget> extends DraftCardDialogController<T> {
    @Override
    public void initialize(MainDatabase md, Choosable<T> dialog, TargetList<T> selectables, TargetType targetType)
    {
        super.initialize(md, dialog, selectables, targetType);
        setChoice(selectables.get(0));
    }

}
