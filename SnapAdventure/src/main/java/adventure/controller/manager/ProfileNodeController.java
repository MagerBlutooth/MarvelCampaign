package adventure.controller.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfileNodeController {

    @FXML
    Label profileNumber;
    @FXML
    Label profileText;

    public void initialize(String profile, String profileNum)
    {
        profileText.setText(profile);
        profileNumber.setText(profileNum);
    }

    public void setText(String profileName) {
        profileText.setText(profileName);
    }
}
