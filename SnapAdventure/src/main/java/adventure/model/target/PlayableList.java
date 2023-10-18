package adventure.model.target;

import snapMain.model.constants.SnapMainConstants;
import snapMain.model.database.PlayableDatabase;
import snapMain.model.target.*;

import java.util.Base64;
import java.util.List;

public class PlayableList extends TargetList<Playable> {

    public PlayableList(List<Playable> cards)
    {
        super(cards);
    }

    public String toSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(SnapTarget t: this)
        {
            stringBuilder.append(t.getTargetType());
            stringBuilder.append(SnapMainConstants.STRING_SEPARATOR);
            stringBuilder.append(t.getID());
            stringBuilder.append(SnapMainConstants.CATEGORY_SEPARATOR);
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
        String[] cardsList = decodedString.split(SnapMainConstants.CATEGORY_SEPARATOR);

        for(String cString: cardsList)
        {
            String[] playableString = cString.split(SnapMainConstants.STRING_SEPARATOR);
            TargetType tType = TargetType.valueOf(playableString[0]);
            if(tType == TargetType.CARD)
            {
                Card c = (Card) db.lookup(Integer.parseInt(playableString[1]), TargetType.CARD);
                this.add(c);
            }
            else
            {
                Token t = (Token) db.lookup(Integer.parseInt(playableString[1]), TargetType.TOKEN);
                InfinityStoneID stoneID = InfinityStoneID.lookupID(t.getID());
                if(stoneID != null)
                {
                    InfinityStone i = new InfinityStone(t.getID(), stoneID);
                    add(i);
                }
                else
                {
                    this.add(t);
                }
            }
        }
    }

    @Override
    public void sort() {

    }

    @Override
    public void setSortMode(String m) {

    }
}
