package main.java.org.example.app.CORPattern;

import main.java.org.example.app.LogAppenderStrategies.LogAppender;
import main.java.org.example.app.enums.Loglevel;
import main.java.org.example.app.utility.LogMessage;

public abstract class LogHandler{
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;
    protected int level;
    protected LogHandler nextLogger;
    protected LogAppender appender;

    public LogHandler(int level,LogAppender appender)
    {
        this.level = level;
        this.appender = appender;
    }


    public void setNextLogger(LogHandler nextHandler){
        this.nextLogger = nextHandler;
    }

    public void logMessage(int level,String message)
    {
        if(this.level>=level)
        {
            Loglevel loglevel = intToLoglevel(level);
            LogMessage logmsg = new LogMessage(loglevel, message);

            if(appender != null)
            {
                appender.append(logmsg);
            }
            write(message);
        }
        else if(nextLogger !=null)
        {
            nextLogger.logMessage(level, message);
        }
    }

    private Loglevel intToLoglevel(int level)
    {
        switch (level) {
            case 1:
                return Loglevel.INFO;
            case 2: 
                return Loglevel.DEBUG;
            case 3:
                return Loglevel.ERROR;
            default:
                return Loglevel.INFO;
        }
    }

    abstract protected void write(String message);

}