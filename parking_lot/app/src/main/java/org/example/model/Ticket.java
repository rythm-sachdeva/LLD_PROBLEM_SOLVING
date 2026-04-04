


package org.example.model;

import java.time.LocalDateTime;

import org.example.enums.PaymentStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ticket {
private String ticketId;
private LocalDateTime entryTime;
private Vehicle vehicle;
private String floorId;
private String spotId;
private PaymentStatus paymentStatus = PaymentStatus.PENDING;
}