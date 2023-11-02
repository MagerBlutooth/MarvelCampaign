package adventure.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class AdvTimekeeper {

    Duration recordedTime;

    LocalDateTime startingTime;

    public AdvTimekeeper()
    {
        startingTime = LocalDateTime.now();
        recordedTime = Duration.ZERO;
    }

    public void savePlayTime()
    {
        Duration currentDuration = Duration.between(startingTime, LocalDateTime.now());
        recordedTime = recordedTime.plus(currentDuration);
        startingTime = LocalDateTime.now();
    }

    public String getTotalPlayTime()
    {
        long totalSecs = recordedTime.getSeconds();
        return String.format("%02d:%02d:%02d", totalSecs / 3600, (totalSecs % 3600) / 60, (totalSecs % 60));
    }

    public String toSaveString()
    {
        savePlayTime();
        return recordedTime.toSeconds()+"";
    }

    public void fromSaveString(String saveString)
    {
        recordedTime = Duration.ofSeconds(Integer.parseInt(saveString));
    }
}
