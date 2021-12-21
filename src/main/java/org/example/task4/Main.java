package org.example.task4;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            list.insert(random.nextInt() % 1000);
        }

        for (int i = 0; i < 5; i++) {
            list.remove(Math.abs(random.nextInt() % list.size()));
        }
    }
}
