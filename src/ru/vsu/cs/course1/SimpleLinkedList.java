package ru.vsu.cs.course1;

import java.util.Iterator;

public class SimpleLinkedList<T> implements Iterable<T> {

    private class SimpleLinkedListItem<T> {
        public T value;
        public SimpleLinkedListItem<T> next;

        public SimpleLinkedListItem(T value, SimpleLinkedListItem<T> next) {
            this.value = value;
            this.next = next;
        }

        public SimpleLinkedListItem(T value) {
            this(value, null);
        }

        public SimpleLinkedListItem() {
            this(null, null);
        }
    }




    private SimpleLinkedListItem<T> head = null;
    private SimpleLinkedListItem<T> tail = null;
    private int count = 0;


    private void checkEmpty() throws SimpleLinkedListException {
        if (isEmpty()) {
            throw new SimpleLinkedListException("List is empty");
        }
    }

    public T getFirst() throws SimpleLinkedListException {
        checkEmpty();
        return head.value;
    }

    public T getLast() throws SimpleLinkedListException {
        checkEmpty();
        return tail.value;
    }

    private SimpleLinkedListItem<T> getItem(int index) throws SimpleLinkedListException {
        if (index < 0 || index >= count) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        SimpleLinkedListItem<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public T get(int index) throws SimpleLinkedListException {
        return getItem(index).value;
    }

    public void addFirst(T value) {
        head = new SimpleLinkedListItem<>(value, head);
        if (tail == null) {
            tail = head;
        }
        count++;
    }

    public void addLast(T value) {
        if (tail == null) {
            head = tail = new SimpleLinkedListItem<>(value);
        } else {
            tail = tail.next = new SimpleLinkedListItem<>(value);
        }
        count++;
    }

    public void insert(int index, T value) throws SimpleLinkedListException {
        if (index < 0 || index > count) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        if (index == 0) {
            addFirst(value);
        } else {
            SimpleLinkedListItem<T> prev = getItem(index - 1);
            prev.next = new SimpleLinkedListItem<>(value, prev.next);
            if (prev.next.next == null) {
                tail = prev.next;
            }
            count++;
        }
    }

    public T removeFirst() throws SimpleLinkedListException {
        checkEmpty();
        T value = getFirst();
        head = head.next;
        if (head == null) {
            tail = null;
        }
        count--;
        return value;
    }

    public T removeLast() throws SimpleLinkedListException {
//        T value = getLast();
//        if (count == 1) {
//            head = tail = null;
//        } else {
//            SimpleLinkedListNode<T> prev = getNode(count - 2);
//            prev.next = null;
//            tail = prev;
//        }
//        count--;
//        return value;
        return remove(count - 1);
    }

    public T remove(int index) throws SimpleLinkedListException {
        if (index == 0) {
            return removeFirst();
        } else {
            SimpleLinkedListItem<T> prev = getItem(index - 1);
            T value = prev.next.value;
            prev.next = prev.next.next;
            if (prev.next == null) {
                tail = prev;
            }
            count--;
            return value;
        }
    }

    public void clear() {
        head = tail = null;
        count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return size() == 0;
    }


    @Override
    public Iterator<T> iterator() {
//        class SimpleLinkedListIterator implements Iterator<T> {
//            SimpleLinkedListItem<T> curr = head;
//
//            @Override
//            public boolean hasNext() {
//                return curr != null;
//            }
//
//            @Override
//            public T next() {
//                T value = curr.value;
//                curr = curr.next;
//                return value;
//            }
//        }
//
//        return new SimpleLinkedListIterator();

        return new Iterator<T>() {
            SimpleLinkedListItem<T> curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T value = curr.value;
                curr = curr.next;
                return value;
            }
        };
    }
}

class SimpleLinkedListException extends Exception {
    public SimpleLinkedListException(String error) {
        super(error);
    }
}
