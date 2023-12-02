package records.view;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import records.view.fxml.FXMLRecordGrabber;
import snapMain.controller.grid.GridActionController;
import snapMain.controller.node.CampaignListNodeController;
import snapMain.model.target.*;
import snapMain.view.ViewSize;


//Node object created for JavaFX nodes that have their own dedicated constructor, to have a built-in initialize method.
public abstract class RecordListNode<T extends SnapTarget> extends StackPane {
    @FXML
    GridRecordDisplayNode<T> list;

    protected FXMLRecordGrabber fxmlRecordGrabber;

    public RecordListNode() {
        fxmlRecordGrabber = new FXMLRecordGrabber();
        fxmlRecordGrabber.grabFXML(getFXMLString(), this, this);
    }

    public void initialize(CardList cards, GridActionController<Card> c, ViewSize v,
                           boolean statusVisible) {
        list.initialize(cards, c, v, statusVisible);
    }

    public <V extends CampaignListNodeController<BaseObject>> V getController() {
        return fxmlRecordGrabber.getController();
    }

    public String getFXMLString() {
        return "listNode.fxml";
    }

    public GridRecordDisplayNode<T> getList()
    {
        return list;
    }

    public void setPrefColumns(int c)
    {
        list.setPrefColumns(c);
    }

    public void refresh()
    {
        list.refresh();
    }


}
