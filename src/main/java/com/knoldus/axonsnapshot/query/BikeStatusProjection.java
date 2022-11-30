package com.knoldus.axonsnapshot.query;

import com.knoldus.axonsnapshot.event.BikeRegisteredEvent;
import com.knoldus.axonsnapshot.event.BikeRentedEvent;
import com.knoldus.axonsnapshot.event.BikeReturnedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BikeStatusProjection {

    private final BikeStatusRepository bikeStatusRepository;

    public BikeStatusProjection(BikeStatusRepository bikeStatusRepository) {
        this.bikeStatusRepository = bikeStatusRepository;
    }

    @EventHandler
    public void on(BikeRegisteredEvent event) {
        log.info("EventHandler BikeRegisteredEvent {}", event.toString());
        bikeStatusRepository.save(new BikeStatus(event.getBikeId(), event.getLocation()));
    }



    @EventHandler
    public void on(BikeRentedEvent event) {
        log.info("EventHandler BikeRentedEvent {}", event.toString());
        bikeStatusRepository.findById(event.getBikeId()).map(bs -> {
            bs.setRenter(event.getRenter());
            return bs;
        });
    }

    @EventHandler
    public void on(BikeReturnedEvent event) {
        log.info("EventHandler BikeReturnedEvent {}", event.toString());
        bikeStatusRepository.findById(event.getBikeId()).map(bs -> {
            bs.setRenter(null);
            bs.setLocation(event.getLocation());
            return bs;
        });
    }

    @QueryHandler(queryName = "findAll")
    public Iterable<BikeStatus> findAll() {
        log.info("QueryHandler findAll");
        return bikeStatusRepository.findAll();
    }

    @QueryHandler(queryName = "findOne")
    public BikeStatus findOne(String bikeId) {
        log.info("QueryHandler findOne");
        return bikeStatusRepository.findById(bikeId).orElse(null);
    }
}
