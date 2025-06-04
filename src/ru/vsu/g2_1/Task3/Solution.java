package ru.vsu.g2_1.Task3;

import java.util.Queue;

public class Solution {


    public static void solve(Queue<Integer> q) throws QueueException {
        if (q.isEmpty()) {
            throw new QueueException("Queue is empty");
        }

        int n = q.size();

        for (int i = 1; i < n; i++){
            int tmp = q.remove();

            for (int j = i; j < n; j++){

                int b = q.remove();

                if (b < tmp){
                    q.add(b);
                } else{
                    q.add(tmp);
                    tmp = b;
                }

            }
            q.add(tmp);

            for (int k = 1; k < i; k++){
                q.add(q.remove());
            }

        }
    }

    // Переворот собственного стека (MyStack)
    public static void reverseStackWithMyStack(MyStack<String> stack) {
        MyStack<String> temp = new MyStack<>();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        // Можно оставить результат во временном стеке, либо вернуть обратно
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    // Переворот стандартного стека (Stack)
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


//        for (int i = 1; i < n; i++){
////            if (i <= 3){
////                tmp = q.remove();
////            }
//            tmp = q.remove();
//
//            for (int j = 1; j < n; j++){
////                if (i >= 4 && j < (j + (i-1)) ){
////                    q.add(q.remove());
////                    continue;
////                }
////                if (i >= 4 && j == i){
////                    tmp = q.remove();
////                }
//
//                int b = q.remove();
//
//                if (b < tmp){
//                    q.add(b);
//                } else{
//                    q.add(tmp);
//                    tmp = b;
//                }
//
//                // j = 6;
//            }
//            q.add(tmp);
//
//        }


//        for (int i = 1; i < n; i++){
//            if (i >= 3){
//                q.add(q.remove());
//            }
//            int tmp = q.remove();
//
//            for (int j = i; j < n; j++){
//
//                int b = q.remove();
//
//                if (b < tmp){
//                    q.add(b);
//                } else{
//                    q.add(tmp);
//                    tmp = b;
//                }
//
//                // j = 6;
//            }
//            q.add(tmp);
//
//////            if (i >= 2){
//////                for (int l = 1; l < i; l++){
//////                    q.add(q.remove());
//////                }
//////            }
//        }


//        for (int i = 1; i < n; i++) {
//
//            int tmp = q.remove();
//
//            for (int j = 1; j < n; j++) {
//
//                int b = q.remove();
//
//                if (b < tmp) {
//                    q.add(b);
//
//                } else {
//                    q.add(tmp);
//                    tmp = b;
//                }
//            }
//
//            q.add(tmp);
//
//
//        }






