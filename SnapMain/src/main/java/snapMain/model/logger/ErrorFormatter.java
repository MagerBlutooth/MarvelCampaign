package snapMain.model.logger;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ErrorFormatter extends Formatter {

    public String format(LogRecord record) {

        StringBuilder builder = new StringBuilder(1000);

        builder.append(formatMessage(record));
        builder.append("\n");
        builder.append(" - ");
        builder.append(record.getSourceClassName());
        return builder.toString();
    }

    public String getHead(Handler h) {
        return super.getHead(h);
    }

    public String getTail(Handler h) {
        return super.getTail(h);
    }

}
