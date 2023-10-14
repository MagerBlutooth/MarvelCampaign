package adventure.controller.manager;

import adventure.model.AdvMainDatabase;
import adventure.model.adventure.Adventure;
import adventure.view.node.InfinityStoneDisplayNode;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ProfileNodeController {

    @FXML
    VBox contentBox;
    @FXML
    Label profileNumberLabel;
    @FXML
    Label profileText;

    public void initialize(String profileName, String profileNum)
    {
        initializeNewProfileNode(profileName, profileNum);
    }

    public void initialize(AdvMainDatabase mainDatabase, Adventure adventure, String profileNum)
    {
        profileNumberLabel.setText(profileNum);
        profileText.setText(adventure.getProfileName());
        Label worldLabel = new Label();
        worldLabel.setOpacity(0.6);
        worldLabel.setText("World " + adventure.getCurrentWorldNum() + "-" +adventure.getCurrentSectionNum());
        contentBox.getChildren().add(worldLabel);
        InfinityStoneDisplayNode infinityStoneDisplayNode = new InfinityStoneDisplayNode();
        infinityStoneDisplayNode.initialize(mainDatabase, adventure.getTeam());
        contentBox.getChildren().add(infinityStoneDisplayNode);
    }

    private void initializeNewProfileNode(String profileName, String profileNum) {
        profileNumberLabel.setText(profileNum);
        profileText.setText(profileName);
    }

    public void setText(String text) {
        profileText.setText(text);
    }
}
