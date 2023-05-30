package view.dialog;

import controller.ControllerDatabase;
import controller.grid.DialogGridActionController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import model.thing.*;
import view.ViewSize;
import view.fxml.FXMLGrabber;
import view.node.GridDisplayNode;
import view.node.control.ControlNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SelectDialog<T extends Thing> extends Dialog<T> {

    @FXML
    StackPane displayPane;
    @FXML
    TextField searchBar;
    @FXML
    GridDisplayNode<T> choices;

    ControllerDatabase controllerDatabase;

    T selection;

    public SelectDialog()
    {
        FXMLGrabber fxmlGrabber = new FXMLGrabber();
        fxmlGrabber.grabFXML("selectDialog.fxml", this.getDialogPane(), this);
        initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
    }

    public void initialize(ControllerDatabase cd)
    {
        controllerDatabase = cd;
    }

    public void setChoice(T t) {
        displayPane.getChildren().clear();
        ControlNode<Thing> viewNode = new ControlNode<>();
        viewNode.setMaxWidth(300.0);
        viewNode.initialize(controllerDatabase, t,controllerDatabase.grabImage(t, t.getThingType()), ViewSize.LARGE, true);
        displayPane.getChildren().add(viewNode);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.getChildren().add(new ControlNode<>());
    }
}
