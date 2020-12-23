package net.intelie.challenges;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

public class EventInsertTest {
    @Test
    public void insertEvent() throws Exception {

        EventStore eventStore = new EventStoreImpl();
    
        LocalDateTime testDateTime = LocalDateTime.now();

        Event event = new Event("teste", testDateTime);


        eventStore.insert(event);

        System.out.println(eventStore.query("teste", LocalDateTime.now(), LocalDateTime.now()));

    }
}