package org.example.model;
import org.example.enums.GateType;


public class EntryGate extends Gate {
    
    public EntryGate(String id)
    {
        super(id);
    }

    @Override
    public GateType getType(){
        return GateType.ENTRY;
    }
   
    

}
