package net.intelie.challenges;

import java.util.List;

public class EventIteratorImpl implements EventIterator {

    List<Event> events;

    Event current;

    int iterator = -1;

    public EventIteratorImpl(List<Event> events) {
        this.events = events;
    }

    @Override
    public boolean moveNext() {
        if (events.isEmpty())
            return false;
        else {

            if (iterator++ < events.size() - 1) {
                current = events.get(iterator);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public Event current() {
        return this.current;
    }

    @Override
    public void remove() {
        if (!events.isEmpty()) {
            events.remove(iterator);
            this.current = null;
        }
    }

    @Override
    public void close() throws Exception {
        events.clear();
    }
}
