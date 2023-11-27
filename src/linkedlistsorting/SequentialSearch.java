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
public class SequentialSearch {
    public static int sequentialSearch(int[] array, int key, boolean printResults) {
        long startTime = System.nanoTime();

        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                long endTime = System.nanoTime();
                if (printResults) {
                    printSearchResults(array, key, i, "Sequential Search", startTime, endTime);
                    System.out.println("La complejidad de Búsqueda Secuencial es O(n)");
                }
                return i;
            }
        }

        long endTime = System.nanoTime();

        if (printResults) {
            printSearchResults(array, key, -1, "Sequential Search", startTime, endTime);
        }

        return -1;
    }
}
