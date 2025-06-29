package ru.vsu.cs.course1;

public class SimpleStackImpl<T> implements SimpleStack<T> {

    private class SimpleLinkedListItem<T> {
        public T value;
        public SimpleLinkedListItem<T> next;

        public SimpleLinkedListItem(T value, SimpleLinkedListItem<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private SimpleLinkedListItem<T> head = null;
    private int size = 0;

    @Override
    public void push(T value) {
        head = new SimpleLinkedListItem<>(value, head);
        size++;
    }

    @Override
    public T pop() throws Exception {
        if (head == null) {
            throw new Exception("Stack is empty");
        }
        T value = head.value;
        head = head.next;
        size--;
        return value;
    }

    @Override
    public T peek() throws Exception {
        if (head == null) {
            throw new Exception("Stack is empty");
        }
        return head.value;
    }

    @Override
    public int size() {
        return size;
    }
}
