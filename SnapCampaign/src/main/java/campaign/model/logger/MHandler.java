package campaign.model.logger;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

public class MHandler extends StreamHandler {

    private final StreamHandler stderrHandler;
    private static final Level ERROR_THRESHOLD = Level.WARNING;
    private static final MFormatter MY_FORMATTER = new MFormatter();
    private static final MFormatter ERROR_MY_FORMATTER = new MFormatter();

    public MHandler() {
        super(System.out, MY_FORMATTER);
        stderrHandler = new StreamHandler(System.err, MY_FORMATTER);
        stderrHandler.setFormatter(ERROR_MY_FORMATTER);
    }

    @Override
    public synchronized void publish(LogRecord record) {
        if (record.getLevel().intValue() <= ERROR_THRESHOLD.intValue()) {
            super.publish(record);
            super.flush();
        } else {
            stderrHandler.publish(record);
            stderrHandler.flush();
        }
    }

}
