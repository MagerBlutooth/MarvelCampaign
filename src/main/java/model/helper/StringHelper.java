package model.helper;

import javafx.scene.paint.Color;

public class StringHelper {

    public static String displayFormat(String string) {
        return string.replace("-", " ");
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
