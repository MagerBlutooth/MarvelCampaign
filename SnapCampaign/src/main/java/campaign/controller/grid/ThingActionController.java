package campaign.controller.grid;

import campaign.controller.MainDatabase;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import campaign.model.thing.Card;
import campaign.model.thing.EffectThing;
import campaign.model.thing.Location;
import campaign.model.thing.Thing;
import campaign.view.IconImage;
import campaign.view.ViewSize;
import campaign.view.node.control.ControlNode;
import campaign.view.node.control.DraggableControlNode;

public abstract class ThingActionController<T extends EffectThing> implements GridActionController<T> {

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
