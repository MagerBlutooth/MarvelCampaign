package adventure.controller.manager;

import adventure.controller.AdvStartPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.AdventureConstants;
import adventure.model.World;
import adventure.model.adventure.Adventure;
import adventure.view.node.InfinityStoneDisplayNode;
import adventure.view.node.ProfileNode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.PrintWriter;

public class ProfileNodeController {

    @FXML
    ProfileNode profileNode;
    @FXML
    VBox deleteButtonBar;
    @FXML
    VBox contentBox;
    @FXML
    Label profileNumberLabel;
    @FXML
    Label profileText;
    String profileFile;

    AdvStartPaneController advStartPaneController;


    public void initialize(String profileName, String profileNum, String pFile)
    {
        initializeNewProfileNode(profileName, profileNum);
        profileFile = pFile;
    }

    public void initialize(AdvMainDatabase mainDatabase, Adventure adventure, String profileNum, String pFile,
                           AdvStartPaneController sController)
    {
        profileNumberLabel.setText(profileNum);
        profileText.setText(adventure.getProfileName());
        profileFile = pFile;
        Label worldLabel = new Label();
        worldLabel.setOpacity(0.6);
        World w = adventure.getCurrentWorld();
        if(!w.isBossRevealed())
            worldLabel.setText("World " + w.getWorldNum() + "-" +adventure.getCurrentSectionNum());
        else
            worldLabel.setText("World " + w.getWorldNum() + " Boss: " + w.getBoss().getName());
        contentBox.getChildren().add(worldLabel);
        InfinityStoneDisplayNode infinityStoneDisplayNode = new InfinityStoneDisplayNode();
        infinityStoneDisplayNode.initialize(mainDatabase, adventure.getTeam());
        contentBox.getChildren().add(infinityStoneDisplayNode);
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteProfile());
        deleteButtonBar.getChildren().add(deleteButton);
        advStartPaneController = sController;
    }

    private void initializeNewProfileNode(String profileName, String profileNum) {
        contentBox.getChildren().clear();
        profileNumberLabel.setText(profileNum);
        profileText.setText(profileName);
        deleteButtonBar.getChildren().clear();
    }

    public void setText(String text) {
        profileText.setText(text);
    }
    public void deleteProfile() {
        File file = new File(profileFile);
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write("");
            printWriter.close();
            initializeNewProfileNode(AdventureConstants.EMPTY_PROFILE, profileNumberLabel.getText());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        advStartPaneController.checkProfile(profileFile, profileNode, Integer.parseInt(profileNumberLabel.getText()));
    }
}
