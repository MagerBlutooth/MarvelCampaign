package controller.grid;

import controller.ControllerDatabase;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import model.thing.Card;
import model.thing.EffectThing;
import model.thing.Location;
import model.thing.Thing;
import view.IconImage;
import view.ViewSize;
import view.node.control.ControlNode;
import view.node.control.DraggableControlNode;

public abstract class ThingActionController<T extends EffectThing> implements GridActionController<T> {

    ControllerDatabase controllerDatabase;
    DraggableThingDisplayController<T> displayController;

    void initialize(ControllerDatabase d, DraggableThingDisplayController<T> c)
    {
        controllerDatabase = d;
        displayController = c;
    }

    @Override
    public void saveGridNode(ControlNode<T> node) {
        displayController.update(node.getSubject());
    }

    @Override
    public ControlNode<T> createControlNode(T t, IconImage i, ViewSize v, boolean blind) {
        ControlNode<T> node = new DraggableControlNode<>(displayController);
        node.initialize(controllerDatabase, t, i, v, blind);
        createTooltip(node);
        createContextMenu(node);
        setMouseEvents(node);
        return node;
    }

    @Override
    public ControllerDatabase getDatabase() {
        return controllerDatabase;
    }

    @Override
    public void createTooltip(ControlNode<T> node) {
        Tooltip tooltip = new Tooltip();
        Thing subject = node.getSubject();
        ImageView imageView = new ImageView(node.getImage());
        imageView.setFitHeight(ViewSize.MEDIUM.getSizeVal());
        imageView.setFitWidth(ViewSize.MEDIUM.getSizeVal());
        tooltip.setShowDelay(new Duration(0.2));
        tooltip.setTextAlignment(TextAlignment.CENTER);
        tooltip.setGraphic(imageView);

        switch(subject.getThingType())
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
