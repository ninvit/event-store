package net.intelie.challenges;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

public class EventTest {
    @Test
    public void thisIsAWarning() throws Exception {
        Event event = new Event("some_type", LocalDateTime.now());
        LocalDateTime testDate = event.getTimestamp();

        System.out.println("Testing auto created Date " + testDate);
        assertEquals(testDate, event.getTimestamp());
        assertEquals("some_type", event.getType());
    }
}