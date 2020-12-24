package net.intelie.challenges;

import org.junit.Test;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
import java.util.List;

public class EventTest {

    EventStore eventStore = new EventStoreImpl();

    LocalDateTime testDateTime = LocalDateTime.now();

    @Test
    public void insertEvent() throws Exception {

        Event event = new Event();
        event.setType("TestType");
        event.setTimestamp(testDateTime);

        Event event2 = new Event();
        event2.setType("TestType2");
        event2.setTimestamp(testDateTime);

        try {
            System.out.println("Testing event insertion: " + event.getType());
            eventStore.insert(event);
            
            System.out.println("Testing event insertion: " + event2.getType());
            eventStore.insert(event2);

            System.out.println(eventStore.getEvents().size() + " events where inserted");

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
    public void listEvents() throws Exception {

        System.out.println("List testing");

    }
}