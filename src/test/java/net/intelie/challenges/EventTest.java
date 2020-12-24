package net.intelie.challenges;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventTest {

    EventStore eventStore = new EventStoreImpl();
    
    DateTimeFormatter dateFormat;

    @Test
    public void insertEvent() throws Exception {

        Event event = new Event();
        event.setType("TestType");
        event.setTimestamp(LocalDateTime.now());

        Event event2 = new Event();
        event2.setType("TestType2");
        event2.setTimestamp(LocalDateTime.now());

        try {
            System.out.println("Testing 1st event insertion: " + event.getType());
            eventStore.insert(event);

            System.out.println("\nTesting 2nd event insertion: " + event2.getType());
            eventStore.insert(event2);

            System.out.println("\n" + eventStore.getEvents().size() + " events where inserted");

        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }

        assertThat(eventStore.getEvents()).isNotNull();
        assertThat(eventStore.getEvents().get(0)).isNotNull();
        assertThat(eventStore.getEvents().get(0).getType()).isEqualTo(event.getType());

        assertThat(eventStore.getEvents().get(1)).isNotNull();
        assertThat(eventStore.getEvents().get(1).getType()).isEqualTo(event2.getType());
    }

    @Test
    public void insertAndListEvents() throws Exception {

        insertEvent();

        System.out.println("\nList testing");

        List<Event> events = eventStore.getEvents();

        assertThat(eventStore.getEvents()).isNotNull();


        for (Event event : events) {
            System.out.println(event.getType() + " " + event.getTimestamp());
        }
    }

    @Test
    public void removeEventByType() throws Exception {

    }
}