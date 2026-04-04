package org.example.model;
import org.example.enums.GateType;


public class ExitGate extends Gate {
    
    public ExitGate(String id)
    {
        super(id);
    }

    @Override
    public GateType getType(){
        return GateType.EXIT;
    }
   
}
