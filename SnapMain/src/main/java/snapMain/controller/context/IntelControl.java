package snapMain.controller.context;

import javafx.scene.control.MenuItem;
import snapMain.model.target.Card;
import snapMain.model.target.EffectBaseObject;

import java.util.ArrayList;
import java.util.List;

public class IntelControl<T extends EffectBaseObject> implements ContextControl<T>
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
