package adventure.view.popup;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdvPopup extends Stage {

    public AdvPopup()
    {
        initModality(Modality.NONE);
        initStyle(StageStyle.UNDECORATED);
    }
}
