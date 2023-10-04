package snapMain.view.node;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import snapMain.model.constants.PlanningPurchase;
import snapMain.model.database.FactionLabel;
import snapMain.view.fxml.FXMLMainGrabber;

public class PlanningSheet extends ScrollPane {

    @FXML
    VBox mainBox;
    public PlanningSheet()
    {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("planningSheet.fxml", this, this);
    }

    public void initialize(FactionLabel f)
    {
        mainBox.getChildren().clear();
        for(PlanningPurchase p: PlanningPurchase.values())
        {
            String title = p.getPrettyString();
            String description = p.getDescription();
            int cost = p.getCost();
            if(p == PlanningPurchase.SUPER) {
                title = f.getSuperPurchaseTitle();
                description = f.getSuperDescription();
            }
            else if(p == PlanningPurchase.HIRE && f == FactionLabel.HYDRA)
                cost++;
            else if(p == PlanningPurchase.BRAINWASH && f == FactionLabel.SHIELD)
                cost +=2;
            addOption(title, cost, description);
        }
    }

    private void addOption(String title, int cost, String description) {
        Label text = new Label(title+": " +cost);
        Label descriptionText = new Label(description);
        descriptionText.setId("info");
        mainBox.getChildren().add(text);
        mainBox.getChildren().add(descriptionText);
    }
}
