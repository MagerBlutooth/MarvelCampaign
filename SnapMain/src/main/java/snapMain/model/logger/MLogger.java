package snapMain.model.logger;

import org.slf4j.Marker;

import java.io.Serializable;

public class MLogger implements org.slf4j.Logger, Serializable {

    public static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(MLogger.class.getName());
    private static final String NAME = "SnapLogger";
    private static final String FORMAT_CONSTANT = "%";

    public MLogger(Class<?> name) {
        LOGGER.setUseParentHandlers(false);
    }

    @Override
    public void debug(String arg0) {
        LOGGER.config(arg0);

    }

    @Override
    public void debug(String arg0, Object arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void debug(String arg0, Object... arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void debug(String arg0, Throwable arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void debug(Marker arg0, String arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void debug(String arg0, Object arg1, Object arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void debug(Marker arg0, String arg1, Object arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void debug(Marker arg0, String arg1, Object... arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void debug(Marker arg0, String arg1, Throwable arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void debug(Marker arg0, String arg1, Object arg2, Object arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void error(String arg0) {
        LOGGER.severe(arg0);

    }

    @Override
    public void error(String arg0, Object arg1) {
        arg0 = arg0.replace(FORMAT_CONSTANT, arg1.toString());
        LOGGER.severe(arg0);

    }

    @Override
    public void error(String arg0, Object... arg1) {
        for (int i = 0; i < arg1.length; i++) {
            arg0 = arg0.replaceFirst(FORMAT_CONSTANT, arg1[i].toString());
        }
        LOGGER.severe(arg0);

    }

    @Override
    public void error(String arg0, Throwable arg1) {
        arg0 = arg0.replace(FORMAT_CONSTANT, arg1.toString());
        LOGGER.severe(arg0);

    }

    @Override
    public void error(Marker arg0, String arg1) {

    }

    @Override
    public void error(String arg0, Object arg1, Object arg2) {
        arg0 = arg0.replaceFirst(FORMAT_CONSTANT, arg1.toString());
        arg0 = arg0.replaceFirst(FORMAT_CONSTANT, arg2.toString());
        LOGGER.severe(arg0);
    }

    @Override
    public void error(Marker arg0, String arg1, Object arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void error(Marker arg0, String arg1, Object... arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void error(Marker arg0, String arg1, Throwable arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void error(Marker arg0, String arg1, Object arg2, Object arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void info(String arg0) {
        LOGGER.info(arg0);
    }

    @Override
    public void info(String arg0, Object arg1) {
        if (arg1 == null)
            arg1 = "null";
        arg0 = arg0.replace(FORMAT_CONSTANT, arg1.toString());
        LOGGER.info(arg0);
    }

    @Override
    public void info(String arg0, Object... arg1) {
        for (int i = 0; i < arg1.length; i++) {
            arg0 = arg0.replaceFirst(FORMAT_CONSTANT, arg1[i].toString());
        }
        LOGGER.info(arg0);

    }

    @Override
    public void info(String arg0, Throwable arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void info(Marker arg0, String arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void info(String arg0, Object arg1, Object arg2) {
        arg0 = arg0.replaceFirst(FORMAT_CONSTANT, arg1.toString());
        arg0 = arg0.replaceFirst(FORMAT_CONSTANT, arg2.toString());
        LOGGER.info(arg0);

    }

    @Override
    public void info(Marker arg0, String arg1, Object arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void info(Marker arg0, String arg1, Object... arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void info(Marker arg0, String arg1, Throwable arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void info(Marker arg0, String arg1, Object arg2, Object arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isDebugEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isDebugEnabled(Marker arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isErrorEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isErrorEnabled(Marker arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isInfoEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isInfoEnabled(Marker arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isTraceEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isTraceEnabled(Marker arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isWarnEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isWarnEnabled(Marker arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void trace(String arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void trace(String arg0, Object arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void trace(String arg0, Object... arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void trace(String arg0, Throwable arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void trace(Marker arg0, String arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void trace(String arg0, Object arg1, Object arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void trace(Marker arg0, String arg1, Object arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void trace(Marker arg0, String arg1, Object... arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void trace(Marker arg0, String arg1, Throwable arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void trace(Marker arg0, String arg1, Object arg2, Object arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void warn(String arg0) {
        LOGGER.warning(arg0);

    }

    @Override
    public void warn(String arg0, Object arg1) {
        arg0 = arg0.replaceFirst(FORMAT_CONSTANT, arg1.toString());
        LOGGER.warning(arg0);

    }

    @Override
    public void warn(String arg0, Object... arg1) {
        for (int i = 0; i < arg1.length; i++) {
            arg0 = arg0.replaceFirst(FORMAT_CONSTANT, arg1[i].toString());
        }
        LOGGER.warning(arg0);

    }

    @Override
    public void warn(String arg0, Throwable arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void warn(Marker arg0, String arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void warn(String arg0, Object arg1, Object arg2) {
        arg0 = arg0.replaceFirst(FORMAT_CONSTANT, arg1.toString());
        arg0 = arg0.replaceFirst(FORMAT_CONSTANT, arg2.toString());
        LOGGER.warning(arg0);

    }

    @Override
    public void warn(Marker arg0, String arg1, Object arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void warn(Marker arg0, String arg1, Object... arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void warn(Marker arg0, String arg1, Throwable arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void warn(Marker arg0, String arg1, Object arg2, Object arg3) {
        // TODO Auto-generated method stub

    }
}
