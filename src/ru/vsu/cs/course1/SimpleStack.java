package ru.vsu.cs.course1;

public interface SimpleStack<T> {
    void push(T value);

    T pop() throws Exception;

    T peek() throws Exception;

    int size();

    default boolean empty() {
        return size() == 0;
    }
}
