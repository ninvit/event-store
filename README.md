# Implement EventStore
 
```java
public interface EventStore {
    void insert(Event event);

    void removeAll(String type);

    EventIterator query(String type, long startTime, long endTime);
}
```
Storing events using a thread-safe solution

Reference:
https://www.codejava.net/java-core/collections/understanding-collections-and-thread-safety-in-java

