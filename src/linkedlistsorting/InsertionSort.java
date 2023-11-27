/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linkedlistsorting;

import static linkedlistsorting.OutputHandler.printResults;

/**
 *
 * @author joelt
 */
public class InsertionSort {
    public static void insertionSort(int[] array) {
        long startTime = System.nanoTime();
        int n = array.length;

        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }

        long endTime = System.nanoTime();
        printResults(array, "Insertion Sort", startTime, endTime);
    }
    
    public static void insertionSort(int[] array, boolean print) {
        long startTime = System.nanoTime();
        int n = array.length;

        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }

        long endTime = System.nanoTime();
        if (print) {
            printResults(array, "Insertion Sort", startTime, endTime);
        }
    }
    
}
