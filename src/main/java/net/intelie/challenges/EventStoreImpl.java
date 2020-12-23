package net.intelie.challenges;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EventStoreImpl implements EventStore {

    // Using Synchronized Wrappers
    // as per this topic's instructions:
    // https://www.codejava.net/java-core/collections/understanding-collections-and-thread-safety-in-java
    List<Event> events = Collections.synchronizedList(new ArrayList<Event>());

    @Override
	public List<Event> getEvents() {
		return events;
    }
    
    @Override
    public void insert(Event event) {
        System.out.println("Testing insertion of an event " + event);
        try {
            events.add(event);
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    @Override
    public void removeAll(String type) {

        System.out.println("Looking for " + type);

        int size = events.size();

        events.removeIf(event -> event.getType().equals(type));
        System.out.println("Removed " + (size - events.size()) + " events");
    }

    @Override
    public EventIterator query(String type, LocalDateTime startTime, LocalDateTime endTime) {
        System.out.println(
        "Seeking for type " + type + 
        " Starting at time " +  startTime + 
        " ending at " + endTime);

        // Filtering by fields using stream
        // Reference:
        // https://www.jrebel.com/blog/using-java-stream-map-and-java-stream-filter
        return new EventIteratorImpl(
                Collections.synchronizedList(events.stream().filter(event -> event.getType().equals(type))
                        .filter(event -> event.getTimestamp().isEqual(startTime) || event.getTimestamp().isAfter(startTime))
                        .filter(event -> event.getTimestamp().isBefore(endTime)).collect(Collectors.toList())));
    }

}
