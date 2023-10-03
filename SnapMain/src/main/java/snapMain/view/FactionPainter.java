package snapMain.view;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import snapMain.model.target.Faction;

public class FactionPainter {

    public void paintFactionText(Label t, Faction f)
    {
        Color color = Color.WHITESMOKE;
       switch(f.getFactionLabel())
       {
           case SHIELD:
               color = Color.CADETBLUE;
               break;
           case HYDRA:
               color = Color.FIREBRICK;
               break;
           case FREE:
               color = Color.WHITESMOKE;
               break;
       }
        t.setTextFill(color);
    }

    public String getFactionColorString(Faction f) {
        Color color = Color.WHITE;
        switch(f.getFactionLabel())
        {
            case SHIELD:
                color = Color.CADETBLUE;
                break;
            case HYDRA:
                color = Color.FIREBRICK;
                break;
            case FREE:
                color = Color.WHITESMOKE;
                break;
        }
        return toHexString(color);
    }

    private static String toHexString(Color color) {
        int r = ((int) Math.round(color.getRed()     * 255)) << 24;
        int g = ((int) Math.round(color.getGreen()   * 255)) << 16;
        int b = ((int) Math.round(color.getBlue()    * 255)) << 8;
        int a = ((int) Math.round(color.getOpacity() * 255));
        return String.format("#%08X", (r + g + b + a));
    }
}
