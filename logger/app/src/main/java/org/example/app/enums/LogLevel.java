package main.java.org.example.app.enums;


public enum Loglevel{
    DEBUG(1),
    INFO(2),
    ERROR(3);

    private final int value;
    
    Loglevel(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }


    public boolean isGreaterOrEqual(Loglevel other)
    {
        return this.value >= other.value;
    }


    

}