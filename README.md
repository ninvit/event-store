# Implement EventStore
 
```java
public interface EventStore {
    void insert(Event event);

    void removeAll(String type);

    EventIterator query(String type, long startTime, long endTime);
}
```
Storing events using a thread-safe solution

References:
https://www.codejava.net/java-core/collections/understanding-collections-and-thread-safety-in-java
https://www.jrebel.com/blog/using-java-stream-map-and-java-stream-filter


