package main.java.org.example.app.LogAppenderStrategies.ConcreteLogAppenders;

import java.io.FileWriter;
import java.io.IOException;

import main.java.org.example.app.LogAppenderStrategies.LogAppender;
import main.java.org.example.app.utility.LogMessage;

public class FileAppender implements LogAppender{
    private final String filepath;

    public FileAppender(String filepath)
    {
        this.filepath = filepath;
    }
   

    @Override 
    public void append(LogMessage logMessage)
    {
        try(FileWriter writer = new FileWriter(filepath,true))
        {
            writer.write(logMessage.toString() + "n");
        }catch(IOException e)
        {
           e.printStackTrace();
        }
    }


}