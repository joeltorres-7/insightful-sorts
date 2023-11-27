/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linkedlistsorting;

import java.util.Arrays;
import java.util.Scanner;
import static linkedlistsorting.SortManager.CYAN;
import static linkedlistsorting.SortManager.GREEN;
import static linkedlistsorting.SortManager.RESET;
import static linkedlistsorting.BinarySearch.binaryOrdering;
import static linkedlistsorting.BinarySearch.binarySearch;
import static linkedlistsorting.BubbleSort.bubbleSort;
import static linkedlistsorting.InsertionSort.insertionSort;
import static linkedlistsorting.QuickSort.quickSort;
import static linkedlistsorting.SelectionSort.selectionSort;
import static linkedlistsorting.SortManager.separatorLine;
import static linkedlistsorting.SequentialSearch.sequentialSearch;
import static linkedlistsorting.ShellSort.shellSort;
import static linkedlistsorting.SortManager.waitUserInput;

/**
 *
 * @author joelt
 */
public class OutputHandler {
    // Home Manager
    
    public static void runHomeMenu(LinkedListManager linkedList, SortManager sortManager) {
        String RESET = "\u001B[0m";
        String GREEN = "\u001B[32m";
        String RED = "\u001B[31m";
        Scanner scanner = new Scanner(System.in);
        int userOption = 0;

        // Menu y Operaciones.

        System.out.println("*** Menu de Usuario ***");
        System.out.println("(1) Agregar nuevo elemento a la lista.");
        System.out.println("(2) Eliminar un elemento de la lista..");
        System.out.println("(3) Visualizar lista.");
        System.out.println("(4) Eliminar toda la lista.");
        System.out.println("(5) Ejecutar metodos de ordenamiento.");
        System.out.println("(6) Salir.");

        userOption = waitUserInput("Opcion: ");

        // Condiciones

        switch (userOption) {
            case 1:
                int userValue = waitUserInput("Ingrese el valor: ");
                System.out.println();

                try {
                    linkedList.addNode(userValue);
                    System.out.println(GREEN + "Elemento agregado con exito!" + RESET);
                } catch (Exception e) {
                    System.out.println(RED +"Recuerde, solo numeros positivos." + RESET);
                }

                break;
            case 2:
                if (linkedList.getSize() != 0) {
                    System.out.println();
                    System.out.println("Ingrese el valor a eliminar.");

                    int deleteValue = waitUserInput("Valor: ");
                    linkedList.removeNode(deleteValue);

                    System.out.println();
                    System.out.println(GREEN + "Elemento eliminado con exito!" + RESET);
                } else {
                    System.out.println();
                    System.out.println(" /\\_/\\ ");
                    System.out.println("( o.o )");
                    System.out.println(" > ^ <");
                    System.out.println(RED + "Ya no hay elementos para eliminar." + RESET);
                }

                System.out.println();
                break;
            case 3:
                if (linkedList.getSize() != 0) {
                    linkedList.displayList();
                } else {
                    System.out.println();
                    System.out.println(" /\\_/\\ ");
                    System.out.println("( o.o )");
                    System.out.println(" > ^ <");
                    System.out.println(RED + "La lista esta vacia." + RESET);
                }

                System.out.println();
                break;
            case 4:
                linkedList.clearList();

                System.out.println();
                System.out.println(GREEN + "El arbol fue eliminado con exito." + RESET);
                System.out.println();

                break;
            case 5:
                sortManager.runSortMenu(linkedList);
                break;
            case 6:
                System.exit(0);
                break;
        }

        runHomeMenu(linkedList, sortManager);
    }
    
    // Sort Manager
    
    public static void printResults(int[] array, String algorithm, long startTime, long endTime) {
        System.out.println("Resultado del ordenamiento (" + algorithm + "): " + Arrays.toString(array));
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " nanosegundos");
        System.out.println("Complejidad del algoritmo: O(n^2) en el peor caso (comparación)");
    }
    
    public static void printSearchResults(int[] array, int key, int position, String algorithm, long startTime, long endTime) {
        System.out.println("Resultado de la búsqueda (" + algorithm + "):");
        System.out.println("Lista: " + Arrays.toString(array));
        System.out.println("Elemento buscado: " + key);
        if (position != -1) {
            System.out.println("Elemento encontrado en la posición: " + position);
        } else {
            System.out.println("Elemento no encontrado en la lista.");
        }
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " nanosegundos");
    }

    // Imprimir todos los metodos, y estadisticas de cada metodo.
    public static void printAllResults(int[] array) {
        System.out.println("* * * * *");
        System.out.println("Parametros de Ejecucion:");
        int searchKey = waitUserInput("Ingrese el valor a buscar en ambos metodos: ");
        System.out.println("* * * * *");
        System.out.println();

        System.out.println("Resultados de todos los métodos:");
        separatorLine(false);

        // (A) Bubble Sort
        int[] bubbleSortArray = array.clone();
        bubbleSort(bubbleSortArray, true);
        separatorLine(true);

        // (B) Selection Sort
        int[] selectionSortArray = array.clone();
        selectionSort(selectionSortArray);
        separatorLine(true);

        // (C) Insertion Sort
        int[] insertionSortArray = array.clone();
        insertionSort(insertionSortArray);
        separatorLine(true);

        // (D) Shell Sort
        int[] shellSortArray = array.clone();
        shellSort(shellSortArray);
        separatorLine(true);

        // (E) QuickSort
        int[] quickSortArray = array.clone();
        quickSort(quickSortArray, 0, quickSortArray.length - 1);

        // Separador entre métodos de ordenamiento y búsqueda
        System.out.println(CYAN);
        separatorLine(false);
        System.out.println("Resultado de busqueda");
        separatorLine(false);
        System.out.println(RESET);

        // (F) Sequential Search
        sequentialSearch(array, searchKey, true);

        // (G) Binary Search
        int[] quickSortedArray = array.clone();
        binaryOrdering(quickSortedArray, 0, quickSortedArray.length - 1);
        binarySearch(quickSortedArray, searchKey, true);

        // Separador entre metricas
        System.out.println(CYAN + "");
        separatorLine(true);
        System.out.println("Resultados de velocidad en ordenamiento y busqueda");
        separatorLine(true);
        System.out.println("" + RESET);

        // Determinar el método de ordenamiento más rápido
        determineFastestSortingMethod(bubbleSortArray, selectionSortArray, insertionSortArray, shellSortArray, quickSortArray);

        // Determinar el método de búsqueda más rápido
        determineFastestSearchMethod(array, searchKey, array, quickSortedArray);

        System.out.println("");
        separatorLine(false);
        System.out.println("Fin de resultados.");
        separatorLine(false);
    }
    
    private static void determineFastestSortingMethod(int[]... arrays) {
        long minTime = Long.MAX_VALUE;
        String fastestMethod = "";

        for (int i = 0; i < arrays.length; i++) {
            long executionTime = getExecutionTime(arrays[i]);
            if (executionTime < minTime) {
                minTime = executionTime;
                fastestMethod = getSortingMethodName(i);
            }
        }

        System.out.println("El método de ordenamiento más rápido fue: " + GREEN + fastestMethod + RESET);
    }

    private static void determineFastestSearchMethod(int[] array, int searchKey, int[] sequentialArray, int[] binaryArray) {
        long minTimeSequential = getSearchExecutionTime(array, searchKey, sequentialArray, false);
        long minTimeBinary = getSearchExecutionTime(array, searchKey, binaryArray, true);

        String fastestMethod = minTimeSequential < minTimeBinary ? "Sequential Search" : "Binary Search";

        System.out.println("El método de búsqueda más rápido fue: " + GREEN + fastestMethod + RESET);
    }

    public static long getExecutionTime(int[] array) {
        long startTime = System.nanoTime();
        bubbleSort(array.clone(), false);  // Usar un algoritmo de ordenamiento para medir el tiempo
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static long getSearchExecutionTime(int[] array, int key, int[] sortedArray, boolean useBinarySearch) {
        long startTime = System.nanoTime();
        if (useBinarySearch && sortedArray.length > 0) {
            binarySearch(sortedArray, key, false);
        } else {
            sequentialSearch(array, key, false);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static String getSortingMethodName(int index) {
        String[] sortingMethodNames = {"Bubble Sort", "Selection Sort", "Insertion Sort", "Shell Sort", "QuickSort"};
        return sortingMethodNames[index];
    }
}
