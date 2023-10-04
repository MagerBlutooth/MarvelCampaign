package adventure.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.DialogGridActionController;
import snapMain.model.target.SnapTarget;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

public class ChooserDialogController<T extends SnapTarget> {

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

    ToggleGroup toggleGroup;

    public void initialize(MainDatabase md, TargetList<T> selectables, TargetType targetType)
    {
        choices = selectables;
        mainDatabase = md;
        toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(toTeamButton, toTempButton);
        DialogGridActionController<T> gridActionController = new DialogGridActionController<>();
        gridActionController.initialize(mainDatabase);
        choiceNodes.initialize(selectables, targetType, gridActionController, ViewSize.SMALL, false);
    }

    public void setChoice(T t) {
        displayPane.getChildren().clear();
        ControlNode<SnapTarget> viewNode = new ControlNode<>();
        viewNode.setMaxWidth(300.0);
        viewNode.initialize(mainDatabase, t, mainDatabase.grabImage(t), ViewSize.LARGE, true);
        displayPane.getChildren().add(viewNode);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.getChildren().add(new ControlNode<>());
    }

    public T getSelection() {
        return selection;
    }
}
