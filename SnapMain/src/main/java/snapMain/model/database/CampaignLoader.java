package snapMain.model.database;

import snapMain.model.constants.SnapMainConstants;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Does not extend Loader because reads txt instead of csv
public class CampaignLoader {

    String loadFile;

    public CampaignLoader() {
        loadFile = SnapMainConstants.CAMPAIGN_FILE;
    }

    public List<String> loadCampaign() {
        try
        {
            List<String> campaignStrings = new ArrayList<>();
            File f = new File(loadFile);
            if(!f.exists())
                f.createNewFile();
            Scanner myReader = new Scanner(f);
            while(myReader.hasNextLine())
            {
                String str = myReader.nextLine();
                if(!str.isBlank())
                    campaignStrings.add(str);
            }
            return campaignStrings;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
