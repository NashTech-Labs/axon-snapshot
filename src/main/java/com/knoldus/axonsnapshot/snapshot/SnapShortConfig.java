package com.knoldus.axonsnapshot.snapshot;

import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnapShortConfig {
    @Bean
    public SnapshotTriggerDefinition bikeSnapshot(Snapshotter snapshotter) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, 1);
    }
}
