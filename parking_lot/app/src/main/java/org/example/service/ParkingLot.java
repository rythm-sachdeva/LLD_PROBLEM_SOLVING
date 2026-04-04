package org.example.service;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import org.example.enums.PaymentMode;
import org.example.enums.PricingStrategyType;
import org.example.factory.PaymentStrategyFactory;
import org.example.factory.PricingStrategyFactory;
import org.example.model.ParkingFloor;
import org.example.model.ParkingSpot;
import org.example.model.Ticket;
import org.example.model.Vehicle;
import org.example.strategy.payment.PaymentStrategy;
import org.example.strategy.pricing.pricingStrategy;

@Getter
public class ParkingLot {
    
    private static final ParkingLot INSTANCE = new ParkingLot();
    private final Map<String,ParkingFloor> parkingFloors = new HashMap<>();
    private final Map<String,Ticket> activeTickets = new HashMap<>();

    @Setter
    private pricingStrategy pricingStrategy;
    
    public ParkingLot(){
        this.pricingStrategy = PricingStrategyFactory.get(PricingStrategyType.TIME_BASED);
    }

    public static ParkingLot getInstance() {
        return INSTANCE;
    }

     public void addFloor(ParkingFloor floor) {
        parkingFloors.put(floor.getId(), floor);
    }
   
    public Ticket parkVehicle(Vehicle vehicle,LocalDateTime entryTime)
    {
        for (ParkingFloor floor: parkingFloors.values())
        {
            ParkingSpot spot = floor.findAvailableSpot(vehicle.getVehicleType());
            if(spot!=null)
            {
                String uuid = UUID.randomUUID().toString();
                Ticket ticket = Ticket.builder()
                .ticketId(uuid)
                .entryTime(entryTime)
                .vehicle(vehicle)
                .floorId(floor.getId())
                .spotId(spot.getId())
                .build();
                activeTickets.put(uuid,ticket);
                System.out.println("Vehicle parked. Ticket: " + uuid);
                return ticket;
            }

        }
        System.out.println("No spot available for vehicle type: " + vehicle.getVehicleType());
        return null;
    }

    public void unparkVehicle(String ticketId,LocalDateTime exitTime,PaymentMode paymentMode)
    {
        Ticket ticket = activeTickets.get(ticketId);
        if(ticket==null)
        {
            System.out.println("Invalid ticket id");
            return;
        }

        double fee = pricingStrategy.calculateFee(ticket.getVehicle().getVehicleType(), ticket.getEntryTime(), exitTime);
        
        PaymentStrategy paymentStrategy = PaymentStrategyFactory.get(paymentMode);
        PaymentProcessor paymentProcessor = new PaymentProcessor(paymentStrategy);
        boolean paid = paymentProcessor.pay(ticket, fee);
        if (!paid) {
            System.out.println("Vehicle cannot exit. Payment unsuccessful.");
            return;
        }
        ParkingSpot spot = parkingFloors.get(ticket.getFloorId()).getSpots().get(ticket.getSpotId());
        spot.vacate();
        activeTickets.remove(ticketId);
        System.out.println("Vehicle exited. Fee charged: ₹" + fee);
       
        
    }

       public void printStatus() {
        parkingFloors.forEach((floorId, floor) -> {
            System.out.println("Floor: " + floorId);
            floor.getSpots().values().forEach(spot -> {
                System.out.println(" Spot " + spot.getId() + " [" + spot.getAllowedType() + "] - " + (spot.isOccupied() ? "Occupied" : "Free"));
            });
        });
    }




    
}
