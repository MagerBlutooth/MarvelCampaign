package campaign.model.database;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import campaign.model.thing.Thing;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public abstract class Loader<T extends Thing> {
    String loadFile;

    public Loader(String file)
    {
        loadFile = file;
        readCSV();
    }

    public List<String[]> readCSV() {
        try
        {
            File f = new File(loadFile);
            if(!f.exists())
                f.createNewFile();
            CSVReader reader = new CSVReader(new FileReader(loadFile));
            return reader.readAll();

        } catch (CsvException | IOException e) {
            throw new RuntimeException(e);
        }

    }

}