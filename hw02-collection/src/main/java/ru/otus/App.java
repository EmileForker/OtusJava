/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.otus;

import java.util.Collections;

public class App {

    public static void main(String[] args) {
        DIYArrayList<Integer> listX = new DIYArrayList<>();
        DIYArrayList<Integer> listY = new DIYArrayList<>();
        DIYArrayList<Integer> listTo = new DIYArrayList<>();

        Collections.addAll(listX, 1, 3, 2, 5, 4, 6, 7, 8, 9, 43, 11, 12, 28, 14, 15, 16, 17, 44, 19, 32, 18);
        listY.add(666);

        Collections.copy(listX, listY);
        Collections.sort(listX);

    }
}
