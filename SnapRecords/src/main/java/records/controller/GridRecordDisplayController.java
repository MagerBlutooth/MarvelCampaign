package records.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import snapMain.controller.grid.GridDisplayController;
import snapMain.model.target.*;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

import java.util.*;

public class GridRecordDisplayController extends GridDisplayController<Card> {

    public void initialize(CardList things, TargetType tType, GridActionController<Card> controller, ViewSize v,
                           boolean sv)
    {
        mainDatabase = controller.getDatabase();
        targetType = tType;
        gridActionController = controller;
        groupList.getChildren().clear();
        viewSize = v;
        targetList = things;
        this.statusVisible = sv;
        populateDisplay();
    }

    protected void populateDisplay()
    {
        groupList.getChildren().clear();
        List<ControlNode<Card>> listOfObjects = new ArrayList<>();
        if(targetList.isEmpty())
        {
            addBlankNode(listOfObjects);
        }
        for(Card c: targetList)
        {
            addNewNode(c, listOfObjects);
        }
        ObservableList<ControlNode<Card>> observableList = FXCollections.observableArrayList(listOfObjects);
        groupList.getChildren().addAll(observableList);
    }

    private void addBlankNode(List<ControlNode<Card>> listOfObjects) {
        ControlNode<Card> n = gridActionController.createEmptyNode(viewSize);
        listOfObjects.add(n);
    }

    public void sort(String m) {
        targetList.setSortMode(m);
        populateDisplay();
    }

    public TargetType getThingType() {
        return targetType;
    }

    public void update(Card c) {
        targetList.replace(c);
        populateDisplay();
    }

    public void refresh() {
        populateDisplay();
    }

    public void setPrefColumns(int c) {
        groupList.setPrefColumns(c);
    }

}
