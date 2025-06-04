package ru.vsu.g2_1.Task3;

public class MyStack<T> {
    private static class Node<T> {
        T value;
        Node<T> next;
        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> top;
    private int size = 0;

    public void push(T value) {
        Node<T> node = new Node<>(value);
        node.next = top;
        top = node;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        T value = top.value;
        top = top.next;
        size--;
        return value;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return top.value;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    // Вспомогательный метод для копирования стека в массив (для вывода)
    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node<T> current = top;
        for (int i = size - 1; i >= 0; i--) {
            arr[i] = current.value;
            current = current.next;
        }
        return arr;
    }
} 