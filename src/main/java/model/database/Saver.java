package model.database;

import com.opencsv.CSVWriter;
import model.thing.Thing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Saver<T extends Thing> {

    ThingDatabase<T> vDatabase;
    String saveFile;

    public Saver(String file, ThingDatabase<T> database)
    {
        saveFile = file;
        vDatabase = database;
    }

    public void writeCSV()
    {
        CSVWriter writer;
        try
        {
            File f = new File(saveFile);
            if(!f.exists())
                f.createNewFile();
            writer = new CSVWriter(new FileWriter(saveFile));
            for(T t: vDatabase)
            {
                String[] s = t.toSaveStringArray();
                writer.writeNext(s);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
