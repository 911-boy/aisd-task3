package ru.vsu.cs.course1;

public class SimpleQueueImpl<T> implements SimpleQueue<T> {

    private class SimpleLinkedListItem<T> {
        public T value;
        public SimpleLinkedListItem<T> next;

        public SimpleLinkedListItem(T value, SimpleLinkedListItem<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private SimpleLinkedListItem<T> head = null;
    private SimpleLinkedListItem<T> tail = null;
    private int size = 0;

    @Override
    public void add(T value) {
        if (head == null) {
            head = tail = new SimpleLinkedListItem<>(value, null);
        } else {
            tail.next = new SimpleLinkedListItem<>(value, null);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public T remove() throws Exception {
        if (head == null) {
            throw new Exception("Queue is empty");
        }
        T value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return value;
    }

    @Override
    public T element() throws Exception {
        if (head == null) {
            throw new Exception("Queue is empty");
        }
        return head.value;
    }

    @Override
    public int size() {
        return size;
    }
}
