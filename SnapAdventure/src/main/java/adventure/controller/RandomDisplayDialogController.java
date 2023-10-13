package adventure.controller;

import adventure.view.popup.Choosable;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import snapMain.controller.MainDatabase;
import snapMain.model.target.SnapTarget;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.menu.FilterMenuButton;
import snapMain.view.menu.SortMenuButton;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

public class RandomDisplayDialogController<T extends SnapTarget> {

    @FXML
    ButtonType okButton;
    @FXML
    ButtonType cancelButton;
    @FXML
    StackPane displayPane;
    @FXML
    GridDisplayNode<T> choiceNodes;
    MainDatabase mainDatabase;
    TargetList<T> choices;
    T selection;
    @FXML
    ToggleButton toTeamButton;
    @FXML
    ToggleButton toTempButton;
    ToggleGroup toggleGroup = new ToggleGroup();

    public void initialize(MainDatabase md, Choosable<T> dialog, TargetList<T> selectables, TargetType targetType)
    {
        choices = selectables;
        mainDatabase = md;
        ChooserDialogGridActionController<T> gridActionController = new ChooserDialogGridActionController<>();
        gridActionController.initialize(mainDatabase, dialog);
        choiceNodes.initialize(selectables, targetType, gridActionController, ViewSize.SMALL, false);
        setChoice(selectables.get(0));
        toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(toTeamButton, toTempButton);
        toggleGroup.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
        toTempButton.setSelected(true);
    }

    public T getSelection() {
        return selection;
    }

    public void setChoice(T t) {
        displayPane.getChildren().clear();
        ControlNode<SnapTarget> viewNode = new ControlNode<>();
        viewNode.setMaxWidth(300.0);
        viewNode.initialize(mainDatabase, t, mainDatabase.grabImage(t), ViewSize.LARGE, true);
        displayPane.getChildren().add(viewNode);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.getChildren().add(new ControlNode<>());
        selection = t;
    }

    public boolean isTeam() {
        return toTeamButton.isSelected();
    }
}
