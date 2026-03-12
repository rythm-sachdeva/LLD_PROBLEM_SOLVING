package main.java.org.example.app.LogAppenderStrategies.ConcreteLogAppenders;
import main.java.org.example.app.LogAppenderStrategies.LogAppender;
import main.java.org.example.app.utility.LogMessage;

public class ConsoleAppender implements LogAppender{
    @Override
    public void append(LogMessage message)
    {
        System.out.println(message);
    }
}