package records.controller;

import snapMain.controller.BasePaneController;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.GridActionController;
import records.view.HallOfFameControlNode;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import snapMain.model.database.TargetDatabase;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import records.view.HallOfFameDisplayNode;
import records.view.TopDisplayNode;
import snapMain.view.node.control.ControlNode;
import records.model.HallOfFameFactory;
import records.view.HallOfFameCreatorPane;
import records.model.HallOfFameEntry;
import records.model.HallOfFameEntryList;

import java.util.ArrayList;
import java.util.List;


public class HallOfFameManagerController extends BasePaneController implements GridActionController<HallOfFameEntry> {

    @FXML
    Button exitButton;
    @FXML
    HallOfFameDisplayNode hallOfFameDisplay;
    @FXML
    TopDisplayNode topDisplay;

    TargetDatabase<HallOfFameEntry> hallOfFameEntries;

    HallOfFameFactory thingFactory = new HallOfFameFactory();
    @Override
    public Scene getCurrentScene() {
        return hallOfFameDisplay.getScene();
    }

    @Override
    public void initialize(MainDatabase db) {

        hallOfFameEntries = thingFactory.loadHallOfFame(db.lookupDatabase(TargetType.CARD));
        mainDatabase = db;
        TargetList<HallOfFameEntry> entriesList = new HallOfFameEntryList(hallOfFameEntries);
        hallOfFameDisplay.initialize(entriesList, TargetType.HALL_OF_FAME, this, ViewSize.MEDIUM, false);
        topDisplay.initialize(mainDatabase, hallOfFameEntries);
    }

    @Override
    public ControlNode<HallOfFameEntry> createControlNode(HallOfFameEntry entry, IconImage i, ViewSize v, boolean blind) {
        HallOfFameControlNode c = new HallOfFameControlNode();
        c.initialize(mainDatabase, entry, i, v, blind);
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
        hallOfFameCreatorPane.initialize(mainDatabase, chosenEntry, otherEntries);
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
        hallOfFameCreatorPane.initialize(mainDatabase, new HallOfFameEntry(mainDatabase.lookupDatabase(TargetType.CARD)), new ArrayList<>(hallOfFameEntries));
        changeScene(hallOfFameCreatorPane);
    }

    @FXML
    public void exitProgram()
    {
        Platform.exit();
    }
}
