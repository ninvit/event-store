package net.intelie.challenges;

import org.junit.Test;

import java.time.LocalDateTime;

public class EventTest {
    @Test
    public void insertEvent() throws Exception {

        EventStore eventStore = new EventStoreImpl();

        LocalDateTime testDateTime = LocalDateTime.now();

        Event event = new Event();
        event.setType("TestType");
        event.setTimestamp(testDateTime);

        eventStore.insert(event);

        Event event2 = new Event();
        event2.setType("TestType2");
        event2.setTimestamp(testDateTime);

        eventStore.insert(event2);

        System.out.println(eventStore.getEvents().get(0).getType());
        
        System.out.println(eventStore.getEvents().get(1).getType());

    }
}