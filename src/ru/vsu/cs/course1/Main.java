package ru.vsu.cs.course1;

import java.util.*;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) throws Exception {
        for (Integer v: Range.range(0, 10)) {
            System.out.print(v + ", ");
        }
        System.out.println();
//        new Range(0, 10).forEach(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer v) {
//                System.out.print(v + ", ");
//            }
//        });
//        System.out.println();
        new Range(0, 10).forEach(v -> System.out.print(v + ", "));
        System.out.println();

        System.out.println("--- SimpleLinkedList ---");
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.addFirst(20);
        list.addFirst(10);
        list.addLast(30);
        list.addLast(40);

        for (int i = 0; i < list.size(); i++) {
            System.out.print((i > 0 ? ", " : "") + list.get(i));
        }
        System.out.println();
        for (Integer v : list) {
            System.out.print(v + ", ");
        }
        System.out.println();

        list.remove(2);
        list.removeFirst();
        list.insert(1, 25);
        int i = 0;
        for (Integer v : list) {
            System.out.print((i > 0 ? ", " : "") + v);
            i++;
        }
        System.out.println();

        System.out.println("--- SimpleLinkedListStack ---");
        SimpleStack<String> stack1 = new SimpleLinkedListStack<>();
        stack1.push("first");
        stack1.push("second");
        stack1.push("third");
        while (!stack1.empty()) {
            System.out.print(stack1.pop() + (stack1.size() > 0 ? ", " : ""));
        }
        System.out.println();
        System.out.println("--- SimpleStackImpl ---");
        SimpleStack<Integer> stack2 = new SimpleLinkedListStack<>();
        stack2.push(1);
        stack2.push(2);
        stack2.push(3);
        while (!stack2.empty()) {
            System.out.print(stack2.pop() + (stack2.size() > 0 ? ", " : ""));
        }
        System.out.println();


        System.out.println("--- SimpleLinkedListQueue ---");
        SimpleQueue<String> queue1 = new SimpleLinkedListQueue<>();
        queue1.add("first");
        queue1.add("second");
        queue1.add("third");
        while (!queue1.empty()) {
            System.out.print(queue1.remove() + (queue1.size() > 0 ? ", " : ""));
        }
        System.out.println();

        System.out.println("--- SimpleQueueImpl ---");
        SimpleQueue<Integer> queue2 = new SimpleLinkedListQueue<>();
        queue2.add(1);
        queue2.add(2);
        queue2.add(3);
        while (!queue2.empty()) {
            System.out.print(queue2.remove() + (queue2.size() > 0 ? ", " : ""));
        }
        System.out.println();
    }
}
