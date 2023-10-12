package snapMain.controller.grid;

import javafx.scene.Node;
import snapMain.controller.MainDatabase;
import snapMain.model.target.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

import java.util.*;

public class GridDisplayController<T extends SnapTarget>  {


    @FXML
    protected TilePane groupList;
    protected TargetList<T> targetList;
    Map<String, Boolean> filterOptionsMap;
    List<String> sortOptions;
    ViewSize viewSize;
    protected MainDatabase mainDatabase;

    GridActionController<T> gridActionController;
    TargetType targetType;
    public GridDisplayController()
    {
        super();
    }

    boolean blind;

    public void initialize(TargetList<T> things, TargetType tType, GridActionController<T> controller, ViewSize v,
                           boolean bl)
    {
        mainDatabase = controller.getDatabase();
        targetType = tType;
        gridActionController = controller;
        groupList.getChildren().clear();
        viewSize = v;
        targetList = things;
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
        targetList.sort();
        if(targetList.isEmpty())
        {
            T blankObject = null;
            switch(targetType)
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
                case CARD_OR_TOKEN:
                    CardOrToken ct = new CardOrToken();
                    try {
                        blankObject = (T) ct.getClass().getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
            addNewNode(blankObject, listOfObjects);
        }
        for(T t: targetList)
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
        IconImage i = mainDatabase.grabImage(t);
        ControlNode<T> n = gridActionController.createControlNode(t, i, viewSize, blind);
        listOfObjects.add(n);
    }

    public void sort(String m) {
        targetList.setSortMode(m);
        populateDisplay();
    }

    public TargetType getThingType() {
        return targetType;
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
        targetList.replace(thing);
        populateDisplay();
    }

    public void refresh(TargetList<T> things) {
        targetList = things;
        populateDisplay();
    }

    public void setPrefColumns(int c) {
        groupList.setPrefColumns(c);
    }

    public void addThing(T t) {
        targetList.add(t);
        populateDisplay();
    }

    public void removeThing(T t)
    {
        targetList.remove(t);
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

    private ControlNode<T> getNode(T t) {
        for(Node node: groupList.getChildren())
        {
            if(node instanceof ControlNode)
            {
                ControlNode<T> cNode = (ControlNode<T>)node;
                if(cNode.getSubject().equals(t))
                    return cNode;
            }
        }
        return null;
    }

    public void toggleNodeLight(T t) {
        getNode(t).toggleNodeLight();
    }

    public void highlightAll() {
        for(Node n: groupList.getChildren())
        {
            if(n instanceof ControlNode)
            {
                ControlNode<T> cNode = (ControlNode<T>)n;
                cNode.setHighlighted(true);
            }
        }
    }

    public void clear() {
        groupList.getChildren().clear();
        populateDisplay();
    }
}
