package net.intelie.challenges;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class EventTest {

    EventStore eventStore = new EventStoreImpl();
    List<Event> events = eventStore.getEvents();

    // List stored events
    private void listEvents() {
        for (Event event : events) {
            System.out.println(event.getType() + " - " + event.getTimestamp());
        }
    }

    // Insertion of 2 events named "TestType" and "TestType2"
    @Test
    public void insertEvents() throws Exception {

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

        insertEvents();

        System.out.println("\nList testing: " + eventStore.getEvents().size() + " event(s) found");

        listEvents();

        assertThat(eventStore.getEvents()).isNotNull();
    }

    @Test
    public void removeEventByType() throws Exception {

        String excludedType = "TestType2";

        insertEvents();

        eventStore.removeAll(excludedType);
        System.out.println("\nEvent with type " + excludedType + " was excluded");

        System.out.println("\nRemaining events: ");
        listEvents();

    }

    public static void main(String[] args) {
        byte[] i = null;
        try {
            System.in.read(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }
}