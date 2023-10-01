package snapMain.model.thing;

import snapMain.model.constants.CampaignConstants;
import snapMain.model.database.PlayableDatabase;
import snapMain.model.database.TargetDatabase;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class PlayableList extends ArrayList<Target> {

    public PlayableList(List<Target> cards)
    {
        this.addAll(cards);
    }

    public String toSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(Target t: this)
        {
            stringBuilder.append(t.getTargetType());
            stringBuilder.append(CampaignConstants.STRING_SEPARATOR);
            stringBuilder.append(t.getID());
            stringBuilder.append(CampaignConstants.CATEGORY_SEPARATOR);
        }
        if(!this.isEmpty())
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        else
            stringBuilder.append(" ");
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String cardString, PlayableDatabase db)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(cardString);
        String decodedString = new String(decodedBytes);
        if(decodedString.isBlank())
            return;
        String[] cardsList = decodedString.split(CampaignConstants.CATEGORY_SEPARATOR);

        for(String cString: cardsList)
        {
            String[] playableString = cString.split(CampaignConstants.STRING_SEPARATOR);
            TargetType tType = TargetType.valueOf(playableString[0]);
            if(tType == TargetType.CARD)
            {
                Card c = (Card) db.lookup(Integer.parseInt(playableString[1]), TargetType.CARD);
                this.add(c);
            }
            else
            {
                Token t = (Token) db.lookup(Integer.parseInt(playableString[1]), TargetType.TOKEN);
                this.add(t);
            }
        }
    }

}
