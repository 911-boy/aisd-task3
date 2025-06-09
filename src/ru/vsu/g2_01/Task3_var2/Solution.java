package ru.vsu.g2_01.Task3_var2;

public class Solution {
    // переворот собственного стека (MyStack)
    public static void reverseStackWithMyStack(MyStack<String> stack) {
        MyStack<String> temp = new MyStack<>();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    // переворот стандартного стека (Stack)
    public static void reverseStackWithStdStack(java.util.Stack<String> stack) {
        java.util.Stack<String> temp = new java.util.Stack<>();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }
} 