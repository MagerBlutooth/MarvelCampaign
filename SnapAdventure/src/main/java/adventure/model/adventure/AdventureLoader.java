package adventure.model.adventure;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdventureLoader {

    String loadFile;

    public AdventureLoader(String file) {
        loadFile = file;
        readFile();
    }

    public List<String> readFile() {
        List<String> fileString = new ArrayList<>();
        try {
            File f = new File(loadFile);
            if (!f.exists())
                f.createNewFile();
            FileInputStream fStream = new FileInputStream(loadFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fStream));
                String line;
                while ((line = br.readLine()) != null) {
                    fileString.add(line);
                }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileString;
    }
}

