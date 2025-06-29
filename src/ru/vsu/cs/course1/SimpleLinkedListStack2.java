package ru.vsu.cs.course1;

public class SimpleLinkedListStack2<T> implements SimpleStack<T> {
    private SimpleLinkedList<T> list = new SimpleLinkedList<>();

    @Override
    public void push(T value) {
        list.addFirst(value);
    }

    @Override
    public T pop() throws Exception {
        return list.removeFirst();
    }

    @Override
    public T peek() throws Exception {
        return list.getFirst();
    }

    @Override
    public int size() {
        return list.size();
    }
}
