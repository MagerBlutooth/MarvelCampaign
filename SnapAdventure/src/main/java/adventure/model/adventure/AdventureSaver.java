package adventure.model.adventure;

import campaign.model.constants.CampaignConstants;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AdventureSaver {

    Adventure adventure;
    String saveFile;

    public AdventureSaver(String file, Adventure a)
    {
        saveFile = file;
        adventure = a;
    }

    public void writeFile()
    {
        FileWriter writer;
        try
        {
            File f = new File(saveFile);
            if(!f.exists())
                f.createNewFile();
            writer = new FileWriter(saveFile);
            List<String> adventureString = adventure.convertToString();
            for(String s: adventureString) {
                writer.write(s+ CampaignConstants.CSV_SEPARATOR);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
