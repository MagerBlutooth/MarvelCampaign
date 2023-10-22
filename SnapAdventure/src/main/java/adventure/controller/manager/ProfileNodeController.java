package adventure.controller.manager;

import adventure.controller.AdvStartPaneController;
import adventure.model.AdvMainDatabase;
import adventure.model.AdventureConstants;
import adventure.model.World;
import adventure.model.adventure.Adventure;
import adventure.view.node.InfinityStoneDisplayNode;
import adventure.view.node.ProfileNode;
import adventure.view.popup.ConfirmationDialog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import snapMain.view.grabber.IconConstant;

import java.io.File;
import java.io.PrintWriter;
import java.util.Optional;

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
    @FXML
    Label worldLabel;
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
        Button deleteButton = createDeleteButton(new ImageView(mainDatabase.grabIcon(IconConstant.CLEAR)));
        deleteButtonBar.getChildren().add(deleteButton);
        advStartPaneController = sController;
    }

    private Button createDeleteButton(ImageView image) {
        Button deleteButton = new Button();
        image.setFitHeight(40);
        image.setFitWidth(30);
        deleteButton.setGraphic(image);
        deleteButton.setOnAction(e -> deleteProfile());
        deleteButton.setId("imageButton");
        deleteButton.setOnMouseEntered(e -> {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1.0);
            deleteButton.setEffect(colorAdjust);
        });
        deleteButton.setOnMouseExited(e -> deleteButton.setEffect(null));
        return deleteButton;
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
        ConfirmationDialog dialog = new ConfirmationDialog("Are you sure you want to delete Profile " +
                profileNumberLabel.getText()+"?");
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.YES) {
            File file = new File(profileFile);
            try {
                PrintWriter printWriter = new PrintWriter(file);
                printWriter.write("");
                printWriter.close();
                initializeNewProfileNode(AdventureConstants.EMPTY_PROFILE, profileNumberLabel.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
            advStartPaneController.checkProfile(profileFile, profileNode, Integer.parseInt(profileNumberLabel.getText()));
        }
    }
}
