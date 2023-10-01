package snapMain.controller.grid;

import snapMain.controller.MainDatabase;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import snapMain.model.thing.Card;
import snapMain.model.thing.EffectBaseObject;
import snapMain.model.thing.Location;
import snapMain.model.thing.BaseObject;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;
import snapMain.view.node.control.DraggableControlNode;

public abstract class ThingActionController<T extends EffectBaseObject> implements GridActionController<T> {

    MainDatabase mainDatabase;
    DraggableThingDisplayController<T> displayController;

    void initialize(MainDatabase d, DraggableThingDisplayController<T> c)
    {
        mainDatabase = d;
        displayController = c;
    }

    @Override
    public void saveGridNode(ControlNode<T> node) {
        displayController.update(node.getSubject());
    }

    @Override
    public ControlNode<T> createControlNode(T t, IconImage i, ViewSize v, boolean blind) {
        ControlNode<T> node = new DraggableControlNode<>(displayController);
        node.initialize(mainDatabase, t, i, v, blind);
        createTooltip(node);
        createContextMenu(node);
        setMouseEvents(node);
        return node;
    }

    @Override
    public MainDatabase getDatabase() {
        return mainDatabase;
    }

    @Override
    public void createTooltip(ControlNode<T> node) {
        Tooltip tooltip = new Tooltip();
        BaseObject subject = node.getSubject();
        ImageView imageView = new ImageView(node.getImage());
        imageView.setFitHeight(ViewSize.MEDIUM.getSizeVal());
        imageView.setFitWidth(ViewSize.MEDIUM.getSizeVal());
        tooltip.setShowDelay(new Duration(0.2));
        tooltip.setTextAlignment(TextAlignment.CENTER);
        tooltip.setGraphic(imageView);

        switch(subject.getTargetType())
        {
            case CARD:
                Card card = (Card)subject;
                tooltip.setText(card.getName());
                break;
            case LOCATION:
                Location location = (Location)subject;
                tooltip.setText(location.getName());
                break;
        }
        Tooltip.install(node, tooltip);
    }
}
