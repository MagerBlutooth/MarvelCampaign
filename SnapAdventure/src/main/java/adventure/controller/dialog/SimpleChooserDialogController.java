package adventure.controller.dialog;

import adventure.view.popup.Choosable;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

public class SimpleChooserDialogController<T extends SnapTarget> {

    @FXML
    Label headerLabel;
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

    public void initialize(MainDatabase md, Choosable<T> dialog, TargetList<T> selectables, TargetType targetType)
    {
        choices = selectables;
        mainDatabase = md;
        ChooserDialogGridActionController<T> gridActionController = new ChooserDialogGridActionController<>();
        gridActionController.initialize(mainDatabase, dialog);
        choiceNodes.initialize(selectables, targetType, gridActionController, ViewSize.MEDIUM,
                false);
        choiceNodes.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        choiceNodes.setPrefColumns(6);
    }

    public void initialize(MainDatabase md, Choosable<T> dialog, TargetList<T> selectables, TargetType targetType,
                           String header)
    {
        this.initialize(md, dialog, selectables, targetType);
        headerLabel.setText(header);
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
