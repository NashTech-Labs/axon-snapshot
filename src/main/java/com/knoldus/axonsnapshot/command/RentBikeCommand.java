package com.knoldus.axonsnapshot.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentBikeCommand {
    @TargetAggregateIdentifier
    String bikeId;
    String renter;
}
