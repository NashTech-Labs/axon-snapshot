package com.knoldus.axonsnapshot.command;

import com.knoldus.axonsnapshot.event.BikeRegisteredEvent;
import com.knoldus.axonsnapshot.event.BikeRentedEvent;
import com.knoldus.axonsnapshot.event.BikeReturnedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.context.annotation.Profile;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate(snapshotTriggerDefinition = "bikeSnapshot")
@Slf4j
public class Bike {

    @AggregateIdentifier
    private String bikeId;

    private boolean isAvailable;

    public Bike() {
    }

    @CommandHandler
    public Bike(RegisterBikeCommand command) {
        log.info("CommandHandler RegisterBikeCommand");
        apply(new BikeRegisteredEvent(command.getBikeId(), command.getLocation()));
    }

    @CommandHandler
    public void handle(RentBikeCommand command) {
        log.info("CommandHandler RentBikeCommand");
        if (!this.isAvailable) {
            throw new IllegalStateException("Bike is already rented");
        }
        apply(new BikeRentedEvent(command.getBikeId(), command.getRenter()));
    }

    @CommandHandler
    public void handle(ReturnBikeCommand command) {
        log.info("CommandHandler ReturnBikeCommand");
        if (this.isAvailable) {
            throw new IllegalStateException("Bike was already returned");
        }
        apply(new BikeReturnedEvent(command.getBikeId(), command.getLocation()));
    }

    @EventSourcingHandler
    protected void handle(BikeRegisteredEvent event) {
        log.info("EventSourcingHandler BikeRegisteredEvent");
        this.bikeId = event.getBikeId();
        this.isAvailable = true;
    }

    @EventSourcingHandler
    protected void handle(BikeReturnedEvent event) {
        log.info("EventSourcingHandler BikeReturnedEvent");
        this.isAvailable = true;
    }

    @EventSourcingHandler
    protected void handle(BikeRentedEvent event) {
        log.info("BikeRentedEvent BikeRentedEvent");
        this.isAvailable = false;
    }
}