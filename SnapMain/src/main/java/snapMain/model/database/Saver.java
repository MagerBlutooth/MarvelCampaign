package snapMain.model.database;

import com.opencsv.CSVWriter;
import snapMain.model.target.BaseObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Saver<T extends BaseObject> {

    TargetDatabase<T> vDatabase;
    String saveFile;

    public Saver(String file, TargetDatabase<T> database)
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
                String[] s = t.toCSVSaveStringArray();
                writer.writeNext(s);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
