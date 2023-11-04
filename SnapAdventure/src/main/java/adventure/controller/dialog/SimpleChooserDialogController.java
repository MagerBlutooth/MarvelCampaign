package adventure.controller.dialog;

import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import adventure.view.popup.CardDisplayPopup;
import adventure.view.popup.Choosable;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import snapMain.controller.MainDatabase;
import snapMain.controller.grid.BaseGridActionController;
import snapMain.model.target.SnapTarget;
import snapMain.model.target.TargetList;
import snapMain.model.target.TargetType;
import snapMain.view.ViewSize;
import snapMain.view.node.GridDisplayNode;
import snapMain.view.node.control.ControlNode;

public class SimpleChooserDialogController<T extends SnapTarget> {

    @FXML
    Label headerLabel;
    @FXML
    ButtonType okButton;
    @FXML
    ButtonType cancelButton;
    @FXML
    Button viewTeamButton;
    @FXML
    StackPane displayPane;
    @FXML
    GridDisplayNode<T> choiceNodes;
    MainDatabase mainDatabase;
    TargetList<T> choices;
    T selection;
    ActiveCardList teamCards;

    public void initialize(MainDatabase md, Choosable<T> dialog, TargetList<T> selectables, ActiveCardList team,
                           TargetType targetType)
    {
        choices = selectables;
        teamCards = team;
        mainDatabase = md;
        ChooserDialogGridActionController<T> gridActionController = new ChooserDialogGridActionController<>();
        gridActionController.initialize(mainDatabase, dialog);
        choiceNodes.initialize(selectables, targetType, gridActionController, ViewSize.MEDIUM,
                false);
        choiceNodes.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        choiceNodes.setPrefColumns(6);
    }

    public void initialize(MainDatabase md, Choosable<T> dialog, TargetList<T> selectables, ActiveCardList team,
                           TargetType targetType, String header)
    {
        this.initialize(md, dialog, selectables, team, targetType);
        headerLabel.setText(header);
    }

    public T getSelection() {
        return selection;
    }

    public void setChoice(T t) {
        displayPane.getChildren().clear();
        ControlNode<SnapTarget> viewNode = new ControlNode<>();
        viewNode.setMaxWidth(300.0);
        viewNode.initialize(mainDatabase, t, mainDatabase.grabImage(t), ViewSize.LARGE, true);
        displayPane.getChildren().add(viewNode);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.getChildren().add(new ControlNode<>());
        selection = t;
    }

    public void viewTeam()
    {
        CardDisplayPopup cardDisplayPopup = new CardDisplayPopup(teamCards,
                viewTeamButton.localToScreen(-325,0.0));
        BaseGridActionController<ActiveCard> baseGridActionController = new BaseGridActionController<>();
        baseGridActionController.initialize(mainDatabase);
        cardDisplayPopup.initialize(baseGridActionController);
        cardDisplayPopup.setPrefCols(12);
        cardDisplayPopup.enableVBar();
        cardDisplayPopup.show();
        cardDisplayPopup.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (! isNowFocused) {
                cardDisplayPopup.hide();
            }
        });
    }
}
