package controller.grid;

import controller.CampaignPaneController;
import controller.ControllerDatabase;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.database.ThingDatabase;
import model.factory.ThingFactory;
import model.thing.HallOfFameEntry;
import model.thing.HallOfFameEntryList;
import model.thing.ThingList;
import model.thing.ThingType;
import view.IconImage;
import view.ViewSize;
import view.node.HallOfFameDisplayNode;
import view.node.TopDisplayNode;
import view.node.control.ControlNode;
import view.node.control.HallOfFameControlNode;
import view.pane.HallOfFameCreatorPane;

import java.util.ArrayList;
import java.util.List;


public class HallOfFameManagerController extends CampaignPaneController implements GridActionController<HallOfFameEntry> {

    @FXML
    Button exitButton;
    @FXML
    HallOfFameDisplayNode hallOfFameDisplay;
    @FXML
    TopDisplayNode topTenDisplay;

    ThingDatabase<HallOfFameEntry> hallOfFameEntries;

    ThingFactory thingFactory = new ThingFactory();
    @Override
    public Scene getCurrentScene() {
        return hallOfFameDisplay.getScene();
    }

    @Override
    public void initialize(ControllerDatabase db) {

        hallOfFameEntries = thingFactory.loadHallOfFame(db.lookupDatabase(ThingType.CARD));
        controllerDatabase = db;
        ThingList<HallOfFameEntry> entriesList = new HallOfFameEntryList(hallOfFameEntries);
        hallOfFameDisplay.initialize(entriesList, ThingType.HALL_OF_FAME, this, ViewSize.MEDIUM, false);
        topTenDisplay.initialize(controllerDatabase, hallOfFameEntries);
    }

    @Override
    public ControlNode<HallOfFameEntry> createControlNode(HallOfFameEntry entry, IconImage i, ViewSize v, boolean blind) {
        HallOfFameControlNode c = new HallOfFameControlNode();
        c.initialize(controllerDatabase, entry, i, v, blind);
        setMouseEvents(c);
        return c;
    }
    @Override
    public void saveGridNode(ControlNode<HallOfFameEntry> node) {

    }

    @Override
    public void createTooltip(ControlNode<HallOfFameEntry> n) {

    }

    @Override
    public void createContextMenu(ControlNode<HallOfFameEntry> n) {

    }


    @Override
    public void setMouseEvents(ControlNode<HallOfFameEntry> controlNode) {
        controlNode.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                editEntry(controlNode);
            }
            e.consume();
        });
        controlNode.setOnMouseEntered(mouseEvent -> {
            if(controlNode.getSubject().isEnabled()) {
                controlNode.lowlight();
            }
        });
        controlNode.setOnMouseExited(mouseEvent -> {
            if(controlNode.getSubject().isEnabled()) {
                controlNode.highlight();
            }
        });
    }


    public void editEntry(ControlNode<HallOfFameEntry> node) {
        HallOfFameCreatorPane hallOfFameCreatorPane = new HallOfFameCreatorPane();
        HallOfFameEntry chosenEntry = node.getSubject();
        List<HallOfFameEntry> otherEntries = getOtherEntries(chosenEntry);
        hallOfFameCreatorPane.initialize(controllerDatabase, chosenEntry, otherEntries);
        changeScene(hallOfFameCreatorPane);
    }

    private List<HallOfFameEntry> getOtherEntries(HallOfFameEntry chosenEntry) {
        List<HallOfFameEntry> otherEntries = new ArrayList<>();
        for(HallOfFameEntry h: hallOfFameEntries)
        {
            if(!h.equals(chosenEntry))
            {
                otherEntries.add(h);
            }
        }
        return otherEntries;
    }

    @FXML
    public void addNewEntry()
    {
        HallOfFameCreatorPane hallOfFameCreatorPane = new HallOfFameCreatorPane();
        hallOfFameCreatorPane.initialize(controllerDatabase, new HallOfFameEntry(controllerDatabase.lookupDatabase(ThingType.CARD)), new ArrayList<>(hallOfFameEntries));
        changeScene(hallOfFameCreatorPane);
    }

    @FXML
    public void exitProgram()
    {
        Platform.exit();
    }
}
