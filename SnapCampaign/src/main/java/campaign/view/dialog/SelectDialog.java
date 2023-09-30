package campaign.view.dialog;

import campaign.controller.MainDatabase;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import campaign.model.thing.Thing;
import campaign.view.ViewSize;
import campaign.view.fxml.FXMLCampaignGrabber;
import campaign.view.node.GridDisplayNode;
import campaign.view.node.control.ControlNode;

public class SelectDialog<T extends Thing> extends Dialog<T> {

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
        ControlNode<Thing> viewNode = new ControlNode<>();
        viewNode.setMaxWidth(300.0);
        viewNode.initialize(mainDatabase, t, mainDatabase.grabImage(t, t.getThingType()), ViewSize.LARGE, true);
        displayPane.getChildren().add(viewNode);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.getChildren().add(new ControlNode<>());
    }
}
