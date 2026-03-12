import main.java.org.example.app.LogAppenderStrategies.LogAppender;
import main.java.org.example.app.enums.Loglevel;

public class LoggerConfig {
    private Loglevel loglevel;
    private LogAppender logAppender;

    public LoggerConfig(Loglevel loglevel,LogAppender logAppender)
    {
        this.loglevel = loglevel;
        this.logAppender = logAppender;
    }

    public Loglevel getLoglevel()
    {
        return loglevel;
     }
    
     public void setLogLevel(Loglevel loglevel)
     {
        this.loglevel = loglevel;
     }

     public LogAppender getLogAppender()
     {
        return logAppender;
     }

     public setLogAppender(LogAppender appender)
     {
        this.logAppender = appender;
     }

}