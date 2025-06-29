package ru.vsu.cs.course1;

public class SimpleLinkedListQueue<T> extends SimpleLinkedList<T> implements SimpleQueue<T> {
    @Override
    public void add(T value) {
        addLast(value);
    }

    @Override
    public T remove() throws Exception {
        return removeFirst();
    }

    @Override
    public T element() throws Exception {
        return getFirst();
    }
}
