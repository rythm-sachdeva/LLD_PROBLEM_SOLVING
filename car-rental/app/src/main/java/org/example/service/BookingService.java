package org.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.example.enums.BookingStatus;
import org.example.enums.VehicleStatus;
import org.example.enums.VehicleType;
import org.example.model.Booking;
import org.example.model.Branch;
import org.example.model.User;
import org.example.model.Vehicle;
import org.example.repository.BookingRepository;
import org.example.repository.BranchRepository;
import org.example.strategy.booking.BookingStrategy;
import org.example.strategy.payment.PaymentStrategy;
import org.example.strategy.pricing.PricingStrategy;

import lombok.Setter;

public class BookingService {

    private static volatile BookingService instance;
    private final BranchRepository branchRepo;
    private final BookingRepository bookingRepo;

    @Setter
    private BookingStrategy bookingStrategy;
    
    @Setter
    private PricingStrategy pricingStrategy;

    private BookingService(BranchRepository branchRepo,
       BookingRepository bookingRepo,
       BookingStrategy bookingStrategy,
       PricingStrategy pricingStrategy
    )
    {
        this.branchRepo = branchRepo;
        this.bookingRepo = bookingRepo;
        this.bookingStrategy = bookingStrategy;
        this.pricingStrategy = pricingStrategy; 
    }

    public static BookingService getInstance(BranchRepository branchRepo,
       BookingRepository bookingRepo,
       BookingStrategy bookingStrategy,
       PricingStrategy pricingStrategy)
       {
        if(instance == null){
            synchronized(BookingService.class)
            {
                if(instance == null)
                {
                    instance = new BookingService(branchRepo, bookingRepo, bookingStrategy, pricingStrategy);
                }
            }
        }
        return instance;
       }


       public Optional<Booking> bookVehicle(String branchId,VehicleType vehicleType,LocalDateTime start,LocalDateTime end,
        User user,
        PaymentStrategy paymentStrategy,
        Branch pickUpBranch,
        Branch dropBranch,
        double distanceKm
       )
       {
        Branch branch = branchRepo.getBranch(branchId);
        if(branch == null)
        {
            System.out.println("Branch not found");
            return Optional.empty();
        }
        List<Vehicle> activeVehicles = branch.getVehicleByType(vehicleType).stream().filter(v-> v.getStatus() == VehicleStatus.AVAILABLE).filter(v->!v.getIsBooked().get()).toList();

        if (activeVehicles.isEmpty()) {
            System.out.println("No active " + vehicleType.name() + " vehicles available.");
            return Optional.empty();
        }

        Vehicle vehicle = bookingStrategy.bookVehicle(activeVehicles);

        if(vehicle == null)
        {
            System.out.println("No vehicle can be booked");
            return Optional.empty();
        }

        double amount = pricingStrategy.calculatePrice(vehicle, start, end, distanceKm);
        Booking booking = Booking.builder()
                .bookingId(UUID.randomUUID().toString())
                .user(user)
                .vehicle(vehicle)
                .pickupBranch(pickUpBranch)
                .dropBranch(dropBranch)
                .startTime(start)
                .endTime(end)
                .amount(amount)
                .build();
            
                PaymentProcessor paymentProcessor = new PaymentProcessor(paymentStrategy);
                if (!paymentProcessor.pay(booking)) {
            System.out.println("Payment failed");
            // Rollback booking if payment fails
            vehicle.getIsBooked().set(false);
            return Optional.empty();
        }

        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepo.addBooking(booking);
        vehicle.incrementBookingCount();
        vehicle.setStatus(VehicleStatus.BOOKED);
        System.out.println(booking);

        return Optional.of(booking);

       }

       public void returnVehicle(String bookingId)
       {
        Optional<Booking> bookingOpt = bookingRepo.getBookingById(bookingId);
        if(bookingOpt.isEmpty())
        {
            throw new RuntimeException("Booking Not Found");
        }

        Booking booking = bookingOpt.get();
         if (booking.getStatus() != BookingStatus.CONFIRMED) {
            throw new RuntimeException("Vehicle is not currently booked");
        }

        booking.setStatus(BookingStatus.COMPLETED);
        booking.getVehicle().getIsBooked().set(false);
        Branch dropBranch = booking.getDropBranch();
        dropBranch.addVehicle(booking.getVehicle());
        System.out.println("Vehicle returned to branch " + dropBranch.getCity() + ": " + booking.getVehicle().getLicensePlate());
       }

    
    
}
