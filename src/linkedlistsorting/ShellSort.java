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
public class ShellSort {
    public static void shellSort(int[] array) {
        long startTime = System.nanoTime();
        int n = array.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                int temp = array[i];

                int j;
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }

                array[j] = temp;
            }
        }

        long endTime = System.nanoTime();
        printResults(array, "Shell Sort", startTime, endTime);
    }
    
    public static void shellSort(int[] array, boolean print) {
        long startTime = System.nanoTime();
        int n = array.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                int temp = array[i];

                int j;
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }

                array[j] = temp;
            }
        }

        long endTime = System.nanoTime();
        if (print) {
            printResults(array, "Shell Sort", startTime, endTime);
        }
    }

}
