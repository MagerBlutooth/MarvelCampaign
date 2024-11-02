package adventure.controller.manager;

import adventure.model.AdvMainDatabase;
import adventure.model.AdvProfile;
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
import snapMain.model.logger.MLogger;
import snapMain.view.grabber.IconConstant;

import java.io.File;
import java.io.PrintWriter;
import java.util.Optional;

import static adventure.model.AdventureConstants.EMPTY_PROFILE;

public class ProfileNodeController {

    AdvMainDatabase mainDatabase;
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
    AdvProfile profile;
    Adventure adventure;
    int profileNum;

    MLogger logger = new MLogger(ProfileNodeController.class);


    public void initialize(String profileName, int pNum, AdvProfile p)
    {
        initializeNewProfileNode(profileName, profileNum+"");
        profile = p;
        profileNum = pNum;
    }

    public void initialize(AdvMainDatabase md, Adventure adventure, int pNum, AdvProfile p)
    {
        mainDatabase = md;
        profileNum = pNum;
        profileNumberLabel.setText(profileNum+"");
        profileText.setText(adventure.getProfileName());
        profile = p;
        worldLabel.setOpacity(0.6);
        World w = adventure.getCurrentWorld();
        if(!w.isBossRevealed())
            worldLabel.setText("World " + w.getWorldNum() + "-" +adventure.getCurrentSectionNum());
        else
            worldLabel.setText("World " + w.getWorldNum() + "      Boss: " + w.getBoss().getName());
        contentBox.getChildren().add(worldLabel);
        InfinityStoneDisplayNode infinityStoneDisplayNode = new InfinityStoneDisplayNode();
        infinityStoneDisplayNode.initialize(mainDatabase, adventure.getTeam());
        contentBox.getChildren().add(infinityStoneDisplayNode);
        Button deleteButton = createDeleteButton(new ImageView(mainDatabase.grabIcon(IconConstant.CLEAR)));
        deleteButtonBar.getChildren().add(deleteButton);
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
        dialog.centerToParent(worldLabel.getScene().getWindow());
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.YES) {
            File file = new File(profile.getProfileFile());
            try {
                PrintWriter printWriter = new PrintWriter(file);
                printWriter.write("");
                printWriter.close();
                initializeNewProfileNode(AdventureConstants.EMPTY_PROFILE, profileNumberLabel.getText());
                clearLog(profile);
                generateAdventure(mainDatabase, profile, profileNum);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    private void clearLog(AdvProfile profile) {
        File file = new File(profile.getLogFile());
        if(file.exists())
        {
            try {
                PrintWriter writer = new PrintWriter(file);
                writer.print("");
                writer.close();
            }
            catch(Exception e)
            {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public void setAdventure(Adventure a) {
        adventure = a;
    }

    public Adventure getAdventure() {
        return adventure;
    }

    public void generateAdventure(AdvMainDatabase mainDatabase, AdvProfile profile, int pNum) {
        adventure = new Adventure(mainDatabase, profile);
        profileNum = pNum;
        String name = adventure.getProfileName();
        if(name == null) {
            adventure.setNewProfile(true);
            initialize(EMPTY_PROFILE,profileNum, profile);
        }
        else {
            initialize(mainDatabase, adventure, profileNum, profile);
        }
    }
}
