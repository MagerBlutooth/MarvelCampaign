package snapMain.model.helper;

import java.io.File;
import java.util.Arrays;

public class FileHelper {

    public static final Character[] INVALID_WINDOWS_SPECIFIC_CHARS = {'"', '*', '<', '>', '?', '|', '/', '\\'};
    public static final Character[] INVALID_UNIX_SPECIFIC_CHARS = {'\000'};

    public static String getExtension(File file) {
        String fileName = file.getName();
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }

    public static String convertToPNGName(String id) {
        if (id == null)
            return null;
        String extension = ".png";
        id = id.replaceAll("\\W+", "");
        return id + extension;
    }

    public static boolean validateFilename(String filename) {
        if (filename == null || filename.isBlank() || filename.length() > 32)
            return false;
        return Arrays.stream(getInvalidChars()).noneMatch(ch -> filename.contains(ch.toString()));

    }

    private static Character[] getInvalidChars() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return INVALID_WINDOWS_SPECIFIC_CHARS;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            return INVALID_UNIX_SPECIFIC_CHARS;
        } else {
            return new Character[]{};
        }
    }

    public static String convertToFolderName(String id) {
        if (id == null)
            return null;
        id = id.replaceAll("[^a-zA-Z0-9]", "");
        id = id.replaceAll(" ", "_");
        id = id.toLowerCase();
        return id;
    }

    public static String convertToMP3Name(String id) {
        if (id == null)
            return null;
        String extension = ".mp3";
        id = id.replaceAll("\\W+", "");
        return id + extension;
    }
}
