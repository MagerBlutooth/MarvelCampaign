package snapMain.view.dialog;

import snapMain.controller.MainDatabase;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import snapMain.model.thing.BaseObject;
import snapMain.view.ViewSize;
import snapMain.view.fxml.FXMLCampaignGrabber;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

public class SelectDialog<T extends BaseObject> extends Dialog<T> {

    @FXML
    StackPane displayPane;
    @FXML
    TextField searchBar;
    @FXML
    GridDisplayNode<T> choices;

    MainDatabase mainDatabase;

    T selection;

    public SelectDialog()
    {
        FXMLCampaignGrabber fxmlCampaignGrabber = new FXMLCampaignGrabber();
        fxmlCampaignGrabber.grabFXML("selectDialog.fxml", this.getDialogPane(), this);
        initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
    }

    public void initialize(MainDatabase cd)
    {
        mainDatabase = cd;
    }

    public void setChoice(T t) {
        displayPane.getChildren().clear();
        ControlNode<BaseObject> viewNode = new ControlNode<>();
        viewNode.setMaxWidth(300.0);
        viewNode.initialize(mainDatabase, t, mainDatabase.grabImage(t, t.getTargetType()), ViewSize.LARGE, true);
        displayPane.getChildren().add(viewNode);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.getChildren().add(new ControlNode<>());
    }
}
