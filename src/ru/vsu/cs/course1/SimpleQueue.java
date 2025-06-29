package ru.vsu.cs.course1;

public interface SimpleQueue<T> {
    void add(T value);

    T remove() throws Exception;

    T element() throws Exception;

    int size();

    default boolean empty() {
        return size() == 0;
    }
}