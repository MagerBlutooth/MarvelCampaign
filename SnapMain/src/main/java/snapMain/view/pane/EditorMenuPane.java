package snapMain.view.pane;

import snapMain.controller.MainDatabase;
import snapMain.controller.EditMenuController;
import snapMain.view.fxml.FXMLMainGrabber;

public class EditorMenuPane extends FullViewPane {

    EditMenuController controller;
    public EditorMenuPane() {
        FXMLMainGrabber fxmlMainGrabber = new FXMLMainGrabber();
        fxmlMainGrabber.grabFXML("editorMenu.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(MainDatabase mainDatabase) {
        controller.initialize(mainDatabase);
    }
}
