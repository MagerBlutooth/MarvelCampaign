package adventure.controller;

import adventure.view.popup.Choosable;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import snapMain.controller.MainDatabase;
import snapMain.model.helper.FileHelper;
import snapMain.model.target.Card;
import snapMain.model.target.SnapTarget;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.menu.FilterMenuButton;
import snapMain.view.menu.SortMenuButton;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

public class ChooserDialogController<T extends SnapTarget> {

    @FXML
    ButtonType okButton;
    @FXML
    ButtonType cancelButton;
    @FXML
    SortMenuButton<T> sortButton;
    @FXML
    FilterMenuButton<T> filterButton;
    @FXML
    StackPane displayPane;
    @FXML
    GridDisplayNode<T> choiceNodes;
    MainDatabase mainDatabase;
    TargetList<T> choices;
    T selection;

    public void initialize(MainDatabase md, Choosable<T> dialog, TargetList<T> selectables, TargetType targetType)
    {
        choices = selectables;
        mainDatabase = md;
        ChooserDialogGridActionController<T> gridActionController = new ChooserDialogGridActionController<>();
        gridActionController.initialize(mainDatabase, dialog);
        choiceNodes.initialize(selectables, targetType, gridActionController, ViewSize.SMALL, false);
        if(sortButton != null && filterButton != null) {
            sortButton.initialize(choiceNodes.getController());
            filterButton.initialize(choiceNodes.getController());
        }
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
}
