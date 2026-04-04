package org.example.model;

import java.util.concurrent.atomic.AtomicBoolean;

import org.example.enums.VehicleType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class ParkingSpot {
  private final String id;
  private final VehicleType allowedType;
  private AtomicBoolean occupied = new AtomicBoolean(false);
  
  public boolean tryOccupy()
  {
    return occupied.compareAndSet(false, true);
  }

  public void vacate()
  {
    occupied.set(false);
  }

  public boolean isOccupied()
  {
    return occupied.get();
  }
    
}