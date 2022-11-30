# Axon snapshot 
When axon aggregates live for a long time, and their state constantly changes, they will generate a 
large number of events. It has to load all these events in to rebuild an aggregate's state that may 
have a big performance impact on the application. A snapshot event is a domain event with the special 
purpose to summarise an arbitrary amount of events into a single one. It is recommended to regularly 
create and store a snapshot event, the event store does not have to return long lists of events.

You can see in this application we have set a snapshot configuration. When Bike events cross the 2 thresholds then the 
snapshot starting and it will be stored in a bike events backup.

```
@Configuration
public class SnapShortConfig {
@Bean
public SnapshotTriggerDefinition bikeSnapshot(Snapshotter snapshotter) {
    return new EventCountSnapshotTriggerDefinition(snapshotter, 2);
}
}
```

## Launch Axon Server
Make sure before this application starts you would be run the axon server with help of docker-compose.yml

``> docker-compose up -d``  
``> docker-compose down``

