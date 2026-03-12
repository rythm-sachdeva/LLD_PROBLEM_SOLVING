package main.java.org.example.app.CORPattern.ConcreteStrategies;
import main.java.org.example.app.CORPattern.LogHandler;
import main.java.org.example.app.LogAppenderStrategies.LogAppender;

public class ErrorLogger extends LogHandler{
 
    public ErrorLogger(int level,LogAppender appender)
    {
        super(level,appender);
    }

    @Override
    protected void write(String message)
    {
        System.out.println("ERROR: " + message);
    }

}