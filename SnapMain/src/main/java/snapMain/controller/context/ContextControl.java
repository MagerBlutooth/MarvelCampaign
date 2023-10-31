package snapMain.controller.context;

import javafx.scene.control.MenuItem;
import snapMain.model.target.EffectBaseObject;

import java.util.List;

public interface ContextControl<T extends EffectBaseObject> {
    List<MenuItem> createMenuItems(T t);
}
