package controller.context;

import javafx.scene.control.MenuItem;
import model.thing.Card;
import model.thing.EffectThing;

import java.util.ArrayList;
import java.util.List;

public class IntelControl<T extends EffectThing> implements ContextControl<T>
{
    public MenuItem setCaptainOption(Card c)
    {
        MenuItem captainOption = new MenuItem("Captain");
        captainOption.setOnAction(actionEvent -> c.setCaptain(!c.isCaptain()));
        return captainOption;
    }

    @Override
    public List<MenuItem> createMenuItems(T t) {
        List<MenuItem> menuItems = new ArrayList<>();
        //if(t instanceof Card)
            //menuItems.add(setCaptainOption((Card)t));
        return menuItems;
    }
}
