/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linkedlistsorting;

import static linkedlistsorting.OutputHandler.printSearchResults;

/**
 *
 * @author joelt
 */
public class BinarySearch {
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }
    
    public static void binaryOrdering(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);

            binaryOrdering(array, low, pi - 1);
            binaryOrdering(array, pi + 1, high);
        }
    }
    
    public static int binarySearch(int[] array, int key, boolean printResults) {
        long startTime = System.nanoTime();
        binaryOrdering(array, 0, array.length - 1);
        int left = 0, right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == key) {
                long endTime = System.nanoTime();
                if (printResults) {
                    printSearchResults(array, key, mid, "Binary Search", startTime, endTime);
                    System.out.println("La complejidad de Búsqueda Binaria es O(log n)");
                }
                return mid;
            }

            if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        long endTime = System.nanoTime();

        if (printResults) {
            printSearchResults(array, key, -1, "Binary Search", startTime, endTime);
        }

        return -1;
    }
}
