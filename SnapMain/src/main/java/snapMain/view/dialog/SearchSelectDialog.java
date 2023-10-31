package snapMain.view.dialog;

import snapMain.controller.MainDatabase;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import snapMain.model.target.BaseObject;
import snapMain.model.target.SnapTarget;
import snapMain.model.target.TargetList;
import snapMain.view.ViewSize;
import snapMain.view.fxml.FXMLMainGrabber;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

public class SearchSelectDialog<T extends SnapTarget> extends Dialog<T> {

    @FXML
    protected StackPane displayPane;
    @FXML
    protected TextField searchBar;
    @FXML
    protected GridDisplayNode<T> choiceNodes;
    protected MainDatabase mainDatabase;
    @FXML
    protected TargetList<T> choices;
    protected T selection;

    public SearchSelectDialog()
    {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("searchSelectDialog.fxml", this.getDialogPane(), this);
        initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
    }

    public void initialize(MainDatabase cd, TargetList<T> objects)
    {
        mainDatabase = cd;
        choices = objects;
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
}
