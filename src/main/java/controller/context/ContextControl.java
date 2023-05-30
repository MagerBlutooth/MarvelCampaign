package controller.context;

import javafx.scene.control.MenuItem;
import model.thing.EffectThing;

import java.util.List;

public interface ContextControl<T extends EffectThing> {
    List<MenuItem> createMenuItems(T t);
}
