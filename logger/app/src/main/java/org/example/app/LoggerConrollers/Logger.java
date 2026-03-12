package main.java.org.example.app.LoggerConrollers;

import java.util.concurrent.ConcurrentHashMap;

import main.java.org.example.app.LogAppenderStrategies.LogAppender;
import main.java.org.example.app.enums.Loglevel;
import main.java.org.example.app.utility.LogMessage;

public class Logger{
    private static final ConcurrentHashMap<String,Logger> instances = new ConcurrentHashMap<>();

    private LoggerConfig config;

    private Logger(Loglevel loglevel,LogAppender logAppender)
    {
        this.config = new LoggerConfig(loglevel,logAppender);
    }

    public static Logger getInstance(Loglevel logLevel,LogAppender appender)
    {
        String key = logLevel.name() + "_" + appender.getClass().getName();

        return instances.computeIfAbsent(key,k-> new Logger(logLevel, appender));
    }

    public void setConfig(LoggerConfig config)
    {
        synchronized (Logger.class)
        {
            this.config = config;
        }
    }

    public void log(Loglevel level,String message)
    {
        if(level.getValue() >= config.getLogLevel().getValue())
        {
            LogMessage logMessage = new LogMessage(level, message);
            config.getLogAppender().append(logMessage);
        }
    }

    public void debug(String Message)
    {
        log(Loglevel.DEBUG,message);
    }
    public void info(String message)
    {
        log(Loglevel.INFO,message);
    }
    public void error(String message){
        log(Loglevel.ERROR,message);
    }
}