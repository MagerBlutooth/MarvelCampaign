package records.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Pair;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.thing.CardView;
import records.view.fxml.FXMLRecordGrabber;

import java.util.List;

import static snapMain.model.constants.SnapMainConstants.BASIC_COST_MAX;
import static snapMain.model.constants.SnapMainConstants.BASIC_COST_MIN;

public class TopDisplayEntry extends StackPane {
    @FXML
    HBox cardBox;

    public TopDisplayEntry()
    {
        FXMLRecordGrabber fxmlRecordGrabber = new FXMLRecordGrabber();
        fxmlRecordGrabber.grabFXML("topDisplayEntry.fxml", this, this);
    }

    public void initialize(int c, List<Pair<IconImage, Integer>> images)
    {
        initializeCostLabel(c);
        for(Pair<IconImage, Integer> imagePair: images) {
            CardView cardView = new CardView();
            cardView.setImage(imagePair.getKey(), ViewSize.TINY);
            //Label timesUsed = new Label(imagePair.getValue()+"");
            //cardBox.getChildren().add(timesUsed);
            cardBox.getChildren().add(cardView);
        }
    }

    private void initializeCostLabel(int c) {
        Label costLabel = new Label();
        costLabel.setTextFill(Color.YELLOW);
        costLabel.setFont(new Font("Avengeance Heroic Avenger", 32));
        if(c == BASIC_COST_MIN)
            costLabel.setText(c+"-");
        else if(c == BASIC_COST_MAX)
            costLabel.setText(c+"+");
        costLabel.setText(c+"");
        cardBox.getChildren().add(costLabel);
    }
}
