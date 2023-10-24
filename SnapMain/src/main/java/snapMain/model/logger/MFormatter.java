package snapMain.model.logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MFormatter extends java.util.logging.Formatter {

    public String format(LogRecord record) {
        StringBuilder builder = new StringBuilder(1000);

        SimpleDateFormat d = new SimpleDateFormat("MM/dd/yy HH:mm");
        Calendar c = Calendar.getInstance();
        String currentDate = d.format(c.getTime());
        builder.append(currentDate + ": ");
        builder.append(formatMessage(record));
        builder.append("\n");
        return builder.toString();
    }

    public String getHead(Handler h) {
        return super.getHead(h);
    }

    public String getTail(Handler h) {
        return super.getTail(h);
    }

}
