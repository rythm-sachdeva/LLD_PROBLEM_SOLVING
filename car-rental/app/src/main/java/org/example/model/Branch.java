package org.example.model;
import org.example.enums.VehicleType;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;


@Getter
@Setter
public class Branch {
private String id;
private String city;

private Map<VehicleType,List<Vehicle>>vehicles = new HashMap<>();

public Branch(String id,String city)
{
    this.id = id;
    this.city = city;
}

public List<Vehicle> getVehicleByType(VehicleType type)
{
    return vehicles.getOrDefault(type, new ArrayList<>());
}

public void addVehicle(Vehicle vehicle)
{
    vehicles.computeIfAbsent(vehicle.getType(),k-> new ArrayList<>()).add(vehicle);
}

public void removeVehicle(Vehicle vehicle)
{
    List<Vehicle> list = vehicles.get(vehicle.getType());
    if(list!=null) list.remove(vehicle); 
}



    
    
}
