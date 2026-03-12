package main.java.org.example.app.utility;

import main.java.org.example.app.enums.Loglevel;

public class LogMessage{
    private final Loglevel level;
    private final String message;
    private final long timeStamp;
    

    public LogMessage(Loglevel level,String message)
    {
        this.level = level;
        this.message= message;
        this.timeStamp = System.currentTimeMillis();
    }

    public Loglevel getLoglevel()
    {
        return this.level;
    }

    public String getMessage()
    {
        return this.message;
    }

    @Override
    public string toString()
    {
        return "[" + level + "]" + this.timeStamp + '-' + message;
    }

}