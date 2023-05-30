package view.pane.editor;

import controller.ControllerDatabase;
import model.thing.*;
import view.ViewSize;

import java.util.Map;

public class EditorPaneFactory {

    private static final Map<ThingType, EditorPaneMaker> EDITORS;

    static {

        EDITORS = Map.of(ThingType.CARD, (d, c) -> {
            CardEditorPane cardEditorPane = new CardEditorPane();
            cardEditorPane.initialize(d, ViewSize.LARGE, (Card) c);
            return cardEditorPane;
        }, ThingType.TOKEN, (t, l) -> {
            TokenEditorPane tokenEditorPane = new TokenEditorPane();
            tokenEditorPane.initialize(t, ViewSize.LARGE, (Token) l);
            return tokenEditorPane;
        }, ThingType.LOCATION, (d, l) -> {
            LocationEditorPane locationEditorPane = new LocationEditorPane();
            locationEditorPane.initialize(d, (Location) l);
            return locationEditorPane;
        });
    }

    public EditorPane createEditorPane(ControllerDatabase database, Thing t)
    {
        EditorPaneMaker paneMaker = EDITORS.get(t.getThingType());
        if(paneMaker == null)
            throw new NullPointerException("Error! Could not find card type to make CardEditorPane!");
        return paneMaker.create(database, t);
    }

    public interface EditorPaneMaker {
       EditorPane create(ControllerDatabase d, Thing t);
    }
}


