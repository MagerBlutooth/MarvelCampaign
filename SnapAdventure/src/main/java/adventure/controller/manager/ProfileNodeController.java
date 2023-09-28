package adventure.controller.manager;

import adventure.model.adventure.Adventure;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfileNodeController {

    @FXML
    Label profileNumber;
    @FXML
    Label profileText;

    public void initialize(String profile, String profileNum, Adventure adventure)
    {
        profileText.setText(profile);
        profileNumber.setText(profileNum);
    }

}
