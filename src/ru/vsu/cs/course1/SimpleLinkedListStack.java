package ru.vsu.cs.course1;

public class SimpleLinkedListStack<T> extends SimpleLinkedList<T> implements SimpleStack<T> {
    @Override
    public void push(T value) {
        addFirst(value);
    }

    @Override
    public T pop() throws Exception {
        return removeFirst();
    }

    @Override
    public T peek() throws Exception {
        return getFirst();
    }
}
