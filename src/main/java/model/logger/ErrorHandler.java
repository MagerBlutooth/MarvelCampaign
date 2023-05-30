package model.logger;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

public class ErrorHandler extends StreamHandler {

    private final Level ERROR_THRESHOLD = Level.WARNING;
    private static final MFormatter ERROR_MY_FORMATTER = new MFormatter();

    public ErrorHandler() {
        super(System.err, ERROR_MY_FORMATTER);
    }

    @Override
    public void publish(LogRecord record) {
        if (record.getLevel().intValue() >= ERROR_THRESHOLD.intValue()) {
            super.publish(record);
            super.flush();
        }
    }


}
