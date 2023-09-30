package campaign.controller.grid;

import campaign.controller.MainDatabase;
import campaign.model.thing.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.node.control.ControlNode;

import java.util.*;

public class GridDisplayController<T extends Thing>  {


    @FXML
    protected TilePane groupList;
    protected ThingList<T> thingList;
    Map<String, Boolean> filterOptionsMap;
    List<String> sortOptions;
    ViewSize viewSize;
    protected MainDatabase mainDatabase;

    GridActionController<T> gridActionController;
    ThingType thingType;
    public GridDisplayController()
    {
        super();
    }

    boolean blind;

    public void initialize(ThingList<T> things, ThingType tType, GridActionController<T> controller, ViewSize v, boolean bl)
    {
        mainDatabase = controller.getDatabase();
        thingType = tType;
        gridActionController = controller;
        groupList.getChildren().clear();
        viewSize = v;
        thingList = things;
        blind = bl;
        sortOptions = new ArrayList<>(tType.getSortOptions());
        setFilterOptions(tType.getFilterOptions());
        populateDisplay();
    }

    private void setFilterOptions(List<String> fOptions) {
        filterOptionsMap = new LinkedHashMap<>();
        for(String f: fOptions)
        {
            filterOptionsMap.put(f, false);
        }
    }

    protected void populateDisplay()
    {
        groupList.getChildren().clear();
        List<ControlNode<T>> listOfObjects = new ArrayList<>();
        thingList.sort();
        if(thingList.isEmpty())
        {
            T blankObject = null;
            switch(thingType)
            {
                case CARD:
                    Card c = new Card();
                    try {
                        blankObject = (T) c.getClass().getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case LOCATION:
                    Location l = new Location();
                    try {
                        blankObject = (T) l.getClass().getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case TOKEN:
                    Token t = new Token();
                    try {
                        blankObject = (T) t.getClass().getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;

            }
            addNewNode(blankObject, listOfObjects);
        }
        for(T t: thingList)
        {
            boolean disabled = false;
            for(String entry: getEnabledFilters())
            {
                if(t.hasAttribute(entry))
                {
                    disabled = false;
                    break;
                }
                else {
                    disabled = true;
                }
            }
            if(!disabled)
                addNewNode(t, listOfObjects);
        }
        ObservableList<ControlNode<T>> observableList = FXCollections.observableArrayList(listOfObjects);
        groupList.getChildren().addAll(observableList);
    }

     protected void addNewNode(T t, List<ControlNode<T>> listOfObjects) {
        IconImage i = mainDatabase.grabImage(t, t.getThingType());
        ControlNode<T> n = gridActionController.createControlNode(t, i, viewSize, blind);
        listOfObjects.add(n);
    }

    public void sort(String m) {
        thingList.setSortMode(m);
        populateDisplay();
    }

    public ThingType getThingType() {
        return thingType;
    }

    public List<String> getSortOptions() {
        return sortOptions;
    }

    public Set<String> getFilterOptionKeys() {
        return filterOptionsMap.keySet();
    }

    public List<String> getEnabledFilters()
    {
        List<String> filters = new ArrayList<>();
        for(Map.Entry<String, Boolean> option: filterOptionsMap.entrySet())
        {
            if(option.getValue())
                filters.add(option.getKey());
        }
        return filters;
    }

    public void filter(String text, boolean remove) {
        filterOptionsMap.replace(text, !remove);
        populateDisplay();
    }

    public void update(T thing) {
        thingList.replace(thing);
        populateDisplay();
    }

    public void refresh(ThingList<T> things) {
        thingList = things;
        populateDisplay();
    }

    public void setPrefColumns(int c) {
        groupList.setPrefColumns(c);
    }

    public void addThing(T t) {
        thingList.add(t);
        populateDisplay();
    }

    public void removeThing(T t)
    {
        thingList.remove(t);
        populateDisplay();
    }

    public MainDatabase getControllerDatabase()
    {
        return mainDatabase;
    }

    protected GridActionController<T> getGridActionController() {
        return gridActionController;
    }

    public boolean isBlind()
    {
        return blind;
    }

    public ViewSize getViewSize()
    {
        return viewSize;
    }
}
