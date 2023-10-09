package adventure.view.popup;

import adventure.model.thing.AdvLocation;
import adventure.model.thing.AdvLocationList;
import adventure.view.fxml.FXMLAdventureGrabber;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.DialogGridActionController;
import snapMain.model.target.*;
import snapMain.view.ViewSize;
import snapMain.view.dialog.SearchSelectDialog;
import snapMain.view.fxml.FXMLMainGrabber;
import snapMain.view.node.control.ControlNode;

import java.util.ArrayList;

public class AdvLocationSearchSelectDialog extends SearchSelectDialog<AdvLocation> {

    public AdvLocationSearchSelectDialog()
    {
        FXMLAdventureGrabber fxmlMainGrabber = new FXMLAdventureGrabber();
        fxmlMainGrabber.grabFXML("advLocationSearchSelectDialog.fxml", this.getDialogPane(), this);
        initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.UNDECORATED);
    }
    @Override
    public void initialize(MainDatabase cd, TargetList<AdvLocation> selectableLocs)
    {
        super.initialize(cd, selectableLocs);

        searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
            AdvLocationList locs = new AdvLocationList(new ArrayList<>());
            for(AdvLocation l: selectableLocs)
            {
                String name = l.getName().toLowerCase();
                String searchString = searchBar.textProperty().get().toLowerCase();
                if(name.contains(searchString))
                    locs.add(l);
            }
            DialogGridActionController<AdvLocation> gridController = new DialogGridActionController<>();
            gridController.initialize(cd, this);
            choiceNodes.initialize(locs, TargetType.LOCATION, gridController, ViewSize.MEDIUM, false);
    });
        setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return selection;
            }
            return null;
        });
    }

    @Override
    public void setChoice(AdvLocation l) {
        displayPane.getChildren().clear();
        ControlNode<SnapTarget> viewNode = new ControlNode<>();
        viewNode.setMaxWidth(300.0);
        viewNode.initialize(mainDatabase, l, mainDatabase.grabImage(l), ViewSize.LARGE, true);
        displayPane.getChildren().add(viewNode);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.getChildren().add(new ControlNode<>());
        selection = l;
    }
}
