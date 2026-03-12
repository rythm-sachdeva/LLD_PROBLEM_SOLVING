package main.java.org.example.app.CORPattern.ConcreteStrategies;
import main.java.org.example.app.CORPattern.LogHandler;
import main.java.org.example.app.LogAppenderStrategies.LogAppender;

public class DebugLogger extends LogHandler{
 
    public DebugLogger(int level,LogAppender appender)
    {
        super(level,appender);
    }

    @Override
    protected void write(String message)
    {
        System.out.println("DEBUG: " + message);
    }

}