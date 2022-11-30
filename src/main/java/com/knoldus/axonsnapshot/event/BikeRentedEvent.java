package com.knoldus.axonsnapshot.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BikeRentedEvent {
    String bikeId;
    String renter;
}
