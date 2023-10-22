package adventure.model.stats;

import snapMain.model.constants.SnapMainConstants;

import java.util.Base64;
import java.util.Comparator;

public class CardStats implements Comparable<CardStats> {
    int wins;
    int losses;
    int escapes;
    int forceRetreats;
    int currentUseStreak;
    int longestUseStreak;

    public CardStats() {
        wins = 0;
        losses = 0;
        escapes = 0;
        forceRetreats = 0;
        currentUseStreak = 0;
    }

    public void updateCardStat(boolean used, MatchResult result) {

        if (used) {
            switch (result) {
                case WIN:
                    wins++;
                    break;
                case LOSE:
                    losses++;
                    break;
                case ESCAPE:
                    escapes++;
                    break;
                case FORCE_RETREAT:
                    forceRetreats++;
                    break;
            }
            currentUseStreak++;
            longestUseStreak = Math.max(currentUseStreak, longestUseStreak);
        } else {
            currentUseStreak = 0;
        }
    }

    public String toSaveString() {
        String saveString = wins +
                SnapMainConstants.STRING_SEPARATOR +
                losses +
                SnapMainConstants.STRING_SEPARATOR +
                escapes +
                SnapMainConstants.STRING_SEPARATOR +
                forceRetreats;
        return Base64.getEncoder().encodeToString(saveString.getBytes());

    }

    public void fromSaveString(String saveString) {
        byte[] decodedBytes = Base64.getDecoder().decode(saveString);
        String decodedString = new String(decodedBytes);
        String[] splitString = decodedString.split(SnapMainConstants.STRING_SEPARATOR);
        wins = Integer.parseInt(splitString[0]);
        losses = Integer.parseInt(splitString[1]);
        escapes = Integer.parseInt(splitString[2]);
        forceRetreats = Integer.parseInt(splitString[3]);
    }

    public int lookupStat(MatchResult result) {
        switch (result) {
            case WIN:
                return wins;
            case LOSE:
                return losses;
            case ESCAPE:
                return escapes;
            case FORCE_RETREAT:
                return forceRetreats;
        }
        return -1;
    }

    public int getCurrentUseStreak() {
        return currentUseStreak;
    }

    @Override
    public int compareTo(CardStats o) {
        int result = Comparator.comparing(CardStats::getTotalMatches).thenComparing(CardStats::getTotalWinsAndEscapes)
                .compare(this, o);
        if (result == 0) {
            return Comparator.comparing(CardStats::getTotalLossesAndForceRetreats).compare(o, this);
        }
        return result;
    }

    private int getTotalLossesAndForceRetreats() {
        return lookupStat(MatchResult.LOSE) + lookupStat(MatchResult.FORCE_RETREAT);
    }

    private int getTotalWinsAndEscapes() {
        return lookupStat(MatchResult.WIN) + lookupStat(MatchResult.ESCAPE);
    }

    public int getTotalMatches() {
        return wins + losses + escapes + forceRetreats;
    }
}
