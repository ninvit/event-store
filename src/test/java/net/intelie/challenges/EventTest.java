package net.intelie.challenges;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class EventTest {

    EventStore eventStore = new EventStoreImpl();
    List<Event> events = eventStore.getEvents();

    Event event = new Event("TestType", LocalDateTime.now());
    Event event2 = new Event("TestType2", LocalDateTime.now().plusNanos(1L));


    // List stored events
    private void listEvents() {
        for (Event event : events) {
            System.out.println(event.getType() + " - " + event.getTimestamp());
        }
    }

    // Insertion of 2 events named "TestType" and "TestType2"
    @Test
    public void insertEvents() throws Exception {


        try {
            System.out.println("Testing 1st event insertion: " + event.getType());
            eventStore.insert(event);

            System.out.println("\nTesting 2nd event insertion: " + event2.getType());
            eventStore.insert(event2);

            System.out.println("\n" + eventStore.getEvents().size() + " events where inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(eventStore.getEvents()).isNotNull();
        assertThat(eventStore.getEvents().get(0)).isNotNull();
        assertThat(eventStore.getEvents().get(0).getType()).isEqualTo(event.getType());

        assertThat(eventStore.getEvents().get(1)).isNotNull();
        assertThat(eventStore.getEvents().get(1).getType()).isEqualTo(event2.getType());
    }

    // Inserting and Listing stored events
    @Test
    public void insertAndListEvents() throws Exception {

        try {insertEvents();

        System.out.println("\nList testing: " + eventStore.getEvents().size() + " event(s) found");

        listEvents();

        assertThat(eventStore.getEvents()).isNotNull();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Simple test to remove "TestType2 from the list"
    @Test
    public void removeEventByType() throws Exception {

        String excludedType = "TestType2";

        try {
            insertEvents();

            eventStore.removeAll(excludedType);
            System.out.println("\nEvent with type " + excludedType + " was excluded");

            System.out.println("\nRemaining events: ");
            listEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listEventsByQuery() throws Exception {
    }

}