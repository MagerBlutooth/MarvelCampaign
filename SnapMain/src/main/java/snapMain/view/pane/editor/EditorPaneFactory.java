package snapMain.view.pane.editor;

import snapMain.controller.MainDatabase;
import snapMain.model.thing.*;
import snapMain.view.ViewSize;

import java.util.Map;

public class EditorPaneFactory {

    private static final Map<TargetType, EditorPaneMaker> EDITORS;

    static {

        EDITORS = Map.of(TargetType.CARD, (d, c) -> {
            CardEditorPane cardEditorPane = new CardEditorPane();
            cardEditorPane.initialize(d, ViewSize.LARGE, (Card) c);
            return cardEditorPane;
        }, TargetType.TOKEN, (t, l) -> {
            TokenEditorPane tokenEditorPane = new TokenEditorPane();
            tokenEditorPane.initialize(t, ViewSize.LARGE, (Token) l);
            return tokenEditorPane;
        }, TargetType.LOCATION, (d, l) -> {
            LocationEditorPane locationEditorPane = new LocationEditorPane();
            locationEditorPane.initialize(d, (Location) l);
            return locationEditorPane;
        });
    }

    public EditorPane createEditorPane(MainDatabase database, BaseObject t)
    {
        EditorPaneMaker paneMaker = EDITORS.get(t.getTargetType());
        if(paneMaker == null)
            throw new NullPointerException("Error! Could not find card type to make CardEditorPane!");
        return paneMaker.create(database, t);
    }

    public interface EditorPaneMaker {
       EditorPane create(MainDatabase d, BaseObject t);
    }
}


