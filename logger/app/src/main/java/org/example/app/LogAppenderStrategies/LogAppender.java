package main.java.org.example.app.LogAppenderStrategies;
import main.java.org.example.app.utility.LogMessage;

public interface LogAppender{
    void append(LogMessage logMessage);
}