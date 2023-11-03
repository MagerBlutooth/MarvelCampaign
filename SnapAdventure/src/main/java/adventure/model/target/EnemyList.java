package adventure.model.target;

import adventure.model.AdvMainDatabase;
import snapMain.model.constants.SnapMainConstants;
import snapMain.model.target.TargetList;

import java.util.Base64;
import java.util.List;

public class EnemyList extends TargetList<Enemy> {

    public EnemyList(List<Enemy> t) {
        super(t);
    }

    @Override
    public void sort() {

    }

    @Override
    public void setSortMode(String m) {

    }

    public String toSaveString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(Enemy e: getThings())
        {
            stringBuilder.append(e.toSaveString());
            stringBuilder.append(SnapMainConstants.CSV_SEPARATOR);
        }
        if(!getThings().isEmpty())
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        else
            stringBuilder.append(" ");
        String result = stringBuilder.toString();
        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    public void fromSaveString(String cardString, AdvMainDatabase db) {
        byte[] decodedBytes = Base64.getDecoder().decode(cardString);
        String decodedString = new String(decodedBytes);
        if (decodedString.isBlank())
            return;
        String[] cardsList = decodedString.split(SnapMainConstants.CATEGORY_SEPARATOR);

        for (String cString : cardsList) {
            String[] playableString = cString.split(SnapMainConstants.STRING_SEPARATOR);
            Enemy enemy = new Enemy();
            enemy.fromSaveString(playableString[0], db);
            this.add(enemy);
        }
    }
}
