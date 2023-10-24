package adventure.controller.dialog;

import adventure.view.popup.Choosable;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import snapMain.controller.MainDatabase;
import snapMain.model.target.*;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

public abstract class AdvSearchSelectDialogController<T extends SnapTarget> {

    @FXML
    ButtonType okButton;
    @FXML
    ButtonType cancelButton;
    @FXML
    StackPane displayPane;
    @FXML
    TextField searchBar;
    @FXML
    GridDisplayNode<T> choiceNodes;
    T choice;
    @FXML
    TargetList<T> choices;
    MainDatabase mainDatabase;
    Choosable<T> searchSelectDialog;

    public void initialize(MainDatabase md, Choosable<T> searchDialog, TargetList<T> selectableCards)
    {
        mainDatabase = md;
        searchSelectDialog = searchDialog;
        choices = selectableCards;
        initializeNodes("");
        searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
            initializeNodes(newValue);
        });
    }

    public abstract void initializeNodes(String text);

    public T getChoice() {
        return choice;
    }

    public void setChoice(T t) {
        displayPane.getChildren().clear();
        ControlNode<SnapTarget> viewNode = new ControlNode<>();
        viewNode.setMaxWidth(300.0);
        viewNode.initialize(mainDatabase, t, mainDatabase.grabImage(t), ViewSize.LARGE, true);
        displayPane.getChildren().add(viewNode);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.getChildren().add(new ControlNode<>());
        choice = t;

    }
}
