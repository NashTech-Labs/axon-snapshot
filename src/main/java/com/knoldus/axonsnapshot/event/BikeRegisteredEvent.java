package com.knoldus.axonsnapshot.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BikeRegisteredEvent {
    String bikeId;
    String location;
}
