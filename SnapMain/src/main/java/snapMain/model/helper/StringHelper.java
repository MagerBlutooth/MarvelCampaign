package snapMain.model.helper;

import javafx.scene.paint.Color;

public class StringHelper {

    public static String toDisplayFormat(String string) {
        string = string.replaceAll("[^A-Za-z0-9]", "");
        return string.replace(" ", "");
    }

    public static String toSimpleFormat(String string)
    {
        string = string.replace("_", " ");
        string = camelCase(string);
        return string;
    }

    public static String camelCase(String string)
    {
        StringBuilder camelCase = new StringBuilder();
        for( String oneString : string.toLowerCase().split("_") )
        {
            camelCase.append( oneString.substring(0,1).toUpperCase() );
            camelCase.append( oneString.substring(1) );
        }
        return camelCase.toString();
    }

    public static String getAttributeSeparator() {
        return "~";
    }

    public static String getPropertySeparator() {
        return ":";
    }

    public static String colorToString(Color color) {
        int r = ((int) Math.round(color.getRed() * 255)) << 24;
        int g = ((int) Math.round(color.getGreen() * 255)) << 16;
        int b = ((int) Math.round(color.getBlue() * 255)) << 8;
        int a = ((int) Math.round(color.getOpacity() * 255));

        return String.format("#%08X", (r + g + b + a));
    }
}
