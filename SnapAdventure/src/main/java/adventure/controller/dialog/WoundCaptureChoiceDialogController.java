package adventure.controller.dialog;

import adventure.model.target.ActiveCard;
import adventure.view.popup.CardDisplayPopup;
import adventure.view.popup.Choosable;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import snapMain.controller.MainDatabase;
import snapMain.model.target.SnapTarget;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.grabber.IconConstant;
import snapMain.view.node.control.ControlNode;

public class WoundCaptureChoiceDialogController extends SimpleChooserDialogController<ActiveCard> {

    @FXML
    Button randomButton;
    @FXML
    RadioButton woundButton;
    @FXML
    RadioButton captureButton;
    @FXML
    RadioButton noneButton;

    ToggleGroup toggleGroup = new ToggleGroup();

    public void initialize(MainDatabase md, Choosable<ActiveCard> dialog, TargetList<ActiveCard> selectables)
    {
        super.initialize(md, dialog, selectables, TargetType.CARD);
        choices = selectables;
        mainDatabase = md;
        toggleGroup.getToggles().addAll(woundButton, captureButton, noneButton);
        toggleGroup.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
        ImageView diceView = new ImageView(md.grabIcon(IconConstant.DICE));
        setRandomButtonGraphic(diceView);
        noneButton.setSelected(true);
        ChooserDialogGridActionController<ActiveCard> gridActionController = new ChooserDialogGridActionController<>();
        gridActionController.initialize(mainDatabase, dialog);
        choiceNodes.initialize(selectables, TargetType.CARD, gridActionController, ViewSize.SMALL,
                false);
        choiceNodes.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    public void setRandomButtonGraphic(ImageView image)
    {
        image.setFitWidth(30);
        image.setFitHeight(30);

        randomButton.setOnMouseEntered(e -> {
            ColorAdjust lightUp = new ColorAdjust();
            lightUp.setSaturation(-1.0);
            lightUp.setHue(1.0);
            image.setEffect(lightUp);
        });
        randomButton.setOnMouseExited(e -> image.setEffect(null));
        randomButton.setGraphic(image);
    }

    public boolean captureOptionSelected()
    {
        return captureButton.isSelected();
    }

    public boolean woundOptionSelected()
    {
        return woundButton.isSelected();
    }

    @FXML
    public void showRandom()
    {
        CardDisplayPopup cardDisplayPopup = new CardDisplayPopup(mainDatabase, choices.getRandom(),
                randomButton.localToScreen(100.0,50.0));
        cardDisplayPopup.show();
        cardDisplayPopup.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                cardDisplayPopup.hide();
            }
        });
    }

    @Override
    public void setChoice(ActiveCard c) {
        displayPane.getChildren().clear();
        ControlNode<SnapTarget> viewNode = new ControlNode<>();
        viewNode.setMaxWidth(300.0);
        viewNode.initialize(mainDatabase, c, mainDatabase.grabImage(c), ViewSize.MEDIUM, true);
        displayPane.getChildren().add(viewNode);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.getChildren().add(new ControlNode<>());
        selection = c;
    }
}
