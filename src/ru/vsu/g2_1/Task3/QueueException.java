package ru.vsu.g2_1.Task3;

public class QueueException extends Exception {
    public QueueException(String queueIsEmpty) {
        super(queueIsEmpty);
    }
}
