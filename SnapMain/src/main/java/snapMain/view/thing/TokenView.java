package snapMain.view.thing;

import snapMain.controller.view.TokenViewController;
import snapMain.model.target.TargetType;
import snapMain.model.target.Token;
import snapMain.view.grabber.TargetImageGrabber;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;

public class TokenView extends ThingView {

    TokenViewController controller;

    public TokenView()
    {
        fxmlMainGrabber.grabFXML("tokenView.fxml", this);
        controller = fxmlMainGrabber.getController();
    }

    public void initialize(Token t, ViewSize size, boolean e)
    {
        viewSize = size;
        editable = e;
        if(editable)
            addDragAndDrop();
        controller.initialize(t, size);
        TargetImageGrabber imageGrabber = new TargetImageGrabber(TargetType.TOKEN);

        image = imageGrabber.grabImage(t.getID());
        setImage(image, size);

    }

    public IconImage getImage() {
        return image;
    }

    public Token getToken() {
        return controller.getToken();
    }
}
