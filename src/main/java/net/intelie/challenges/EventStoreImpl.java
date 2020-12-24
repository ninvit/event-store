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
	public synchronized List<Event> getEvents() {
		return events;
    }
    
    @Override
    public synchronized void insert(Event event) {
        try {
            events.add(event);
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    @Override
    public synchronized void removeAll(String type) {
        events.removeIf(event -> event.getType().equals(type));
    }

    @Override
    public synchronized EventIterator query(String type, LocalDateTime startTime, LocalDateTime endTime) {
        // Filtering by fields using stream
        // Reference:
        // https://www.jrebel.com/blog/using-java-stream-map-and-java-stream-filter
        return new EventIteratorImpl(
                Collections.synchronizedList(events.stream().filter(event -> event.getType().equals(type))
                        .filter(event -> event.getTimestamp().isEqual(startTime) || event.getTimestamp().isAfter(startTime))
                        .filter(event -> event.getTimestamp().isBefore(endTime)).collect(Collectors.toList())));
    }

}
