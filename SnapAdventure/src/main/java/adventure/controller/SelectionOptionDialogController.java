package adventure.controller;

import adventure.model.target.ActiveCard;
import adventure.model.target.ActiveCardList;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.target.CardAttribute;
import snapMain.model.target.TargetList;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SelectionOptionDialogController {

    @FXML
    HBox selectionCountBox;
    ActiveCardList selectables;
    @FXML
    ChoiceBox<Integer> minCostChoice;
    @FXML
    ChoiceBox<Integer> maxCostChoice;
    @FXML
    ChoiceBox<Integer> minPowerChoice;
    @FXML
    ChoiceBox<Integer> maxPowerChoice;
    @FXML
    ChoiceBox<Integer> minPoolChoice;
    @FXML
    ChoiceBox<Integer> maxPoolChoice;
    @FXML
    RadioButton powerOnButton;
    @FXML
    RadioButton costOnButton;
    @FXML
    RadioButton poolOnButton;
    @FXML
    RadioButton attributeOnButton;
    @FXML
    ListView<CardAttribute> attributeChecklist;

    ChoiceBox<Integer> selectionCount;
    boolean multiSelect;

    Map<CardAttribute, ObservableValue<Boolean>> map = new HashMap<>();

    public void initialize(TargetList<ActiveCard> s, boolean multiple)
    {
        selectables = new ActiveCardList(s.getThings());
        if(multiple)
            initializeMultiBox();
        initializeCostBox();
        initializePowerBox();
        initializePoolBox();
        initializeAttributeList();
    }

    private void initializeMultiBox() {
        selectionCountBox.getChildren().add(new Label("Amount: "));
        selectionCount = new ChoiceBox<>();
        selectionCount.setValue(1);
        selectionCount.getItems().setAll(
                IntStream.rangeClosed(1,SnapMainConstants.MAX_SELECTION_NUM).boxed().collect(Collectors.toList()));
        selectionCountBox.getChildren().add(selectionCount);
        multiSelect = true;
    }

    private void initializeCostBox() {
        for(int i = SnapMainConstants.MIN_COST; i <= SnapMainConstants.MAX_COST; i++)
        {
            minCostChoice.getItems().add(i);
            maxCostChoice.getItems().add(i);
        }
        minCostChoice.setValue(SnapMainConstants.MIN_COST);
        maxCostChoice.setValue(SnapMainConstants.MAX_COST);
        minCostChoice.getSelectionModel().selectedIndexProperty().addListener((observableValue, old_val, new_val) -> {
            if (new_val.intValue() > maxCostChoice.getValue()) {
                maxCostChoice.getSelectionModel().select((Integer)new_val);
            }
        });
        maxCostChoice.getSelectionModel().selectedIndexProperty().addListener((observableValue, old_val, new_val) -> {
            if (new_val.intValue() < minCostChoice.getValue()) {
                minCostChoice.getSelectionModel().select((Integer)new_val);
            }
        });

    }

    private void initializePowerBox() {
        for(int i = SnapMainConstants.MIN_POWER; i <= SnapMainConstants.MAX_POWER; i++)
        {
            minPowerChoice.getItems().add(i);
            maxPowerChoice.getItems().add(i);
        }
        minPowerChoice.setValue(SnapMainConstants.MIN_POWER);
        maxPowerChoice.setValue(SnapMainConstants.MAX_POWER);
        minPowerChoice.getSelectionModel().selectedIndexProperty().addListener((observableValue, old_val, new_val) -> {
            if (new_val.intValue() > maxPowerChoice.getValue()) {
                maxPowerChoice.getSelectionModel().select((Integer)new_val);
            }
        });
        maxPowerChoice.getSelectionModel().selectedIndexProperty().addListener((observableValue, old_val, new_val) -> {
            if (new_val.intValue() < minPowerChoice.getValue()) {
                minPowerChoice.getSelectionModel().select((Integer)new_val);
            }
        });
    }


    private void initializePoolBox() {
        for(int i = SnapMainConstants.MIN_POOL; i <= SnapMainConstants.MAX_POOL; i++)
        {
            minPoolChoice.getItems().add(i);
            maxPoolChoice.getItems().add(i);
        }
        minPoolChoice.setValue(SnapMainConstants.MIN_POOL);
        maxPoolChoice.setValue(SnapMainConstants.MAX_POOL);
        minPoolChoice.getSelectionModel().selectedIndexProperty().addListener((observableValue, old_val, new_val) -> {
            if (new_val.intValue() > maxPoolChoice.getValue()) {
                maxPoolChoice.getSelectionModel().select((Integer)new_val);
            }
        });
        maxPoolChoice.getSelectionModel().selectedIndexProperty().addListener((observableValue, old_val, new_val) -> {
            if (new_val.intValue() < minPoolChoice.getValue()) {
                minPoolChoice.getSelectionModel().select((Integer)new_val);
            }
        });
    }

    private void initializeAttributeList() {
        for(CardAttribute attribute: CardAttribute.values())
        {
            attributeChecklist.getItems().add(attribute);
        }
        attributeChecklist.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Callback<CardAttribute, ObservableValue<Boolean>> itemToBoolean = (CardAttribute item) -> map.get(item);
        attributeChecklist.setCellFactory(lv -> new MyCell(itemToBoolean));
    }

    //TODO: Filter selectables based on enabled radio buttons
    public TargetList<ActiveCard> getSelectables() {
        if(costOnButton.isSelected())
        {
            selectables = selectables.filterCost(minCostChoice.getValue(),
                    maxCostChoice.getValue());
        }
        if(powerOnButton.isSelected())
        {
            selectables = selectables.filterPower(minPowerChoice.getValue(),
                    maxPowerChoice.getValue());
        }
        if(poolOnButton.isSelected())
        {
            selectables = selectables.filterPool(minPoolChoice.getValue(),
                    maxPoolChoice.getValue());
        }
        if(attributeOnButton.isSelected())
        {
            selectables = selectables.filterAttributes(attributeChecklist.getItems());
        }
        return selectables;
    }

    public boolean isMultiple() {
        return multiSelect && selectionCount.getValue() > 1;
    }

    public int getSelectionCount() {
        if(!isMultiple())
            return 1;
        return selectionCount.getValue();
    }

    public static class MyCell extends CheckBoxListCell<CardAttribute> {
        public MyCell(Callback<CardAttribute, ObservableValue<Boolean>> getSelectedProperty){
            super(getSelectedProperty);
        }

        @Override
        public void updateItem(CardAttribute att, boolean empty) {
            super.updateItem(att, empty);
        }
    }
}
