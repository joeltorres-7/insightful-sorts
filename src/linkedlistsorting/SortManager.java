/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linkedlistsorting;

import static linkedlistsorting.OutputHandler.printAllResults;
import static linkedlistsorting.BinarySearch.binarySearch;
import static linkedlistsorting.BubbleSort.bubbleSort;
import static linkedlistsorting.InsertionSort.insertionSort;
import static linkedlistsorting.QuickSort.quickSort;
import static linkedlistsorting.SelectionSort.selectionSort;
import static linkedlistsorting.SortManager.separatorLine;
import static linkedlistsorting.SequentialSearch.sequentialSearch;
import static linkedlistsorting.ShellSort.shellSort;

import java.util.Scanner;

public class SortManager {
    private BubbleSort bubbleSort;
    private SelectionSort selectionSort;
    private InsertionSort insertionSort;
    private QuickSort quickSort;
    
    static String RESET = "\u001B[0m";
    static String GREEN = "\u001B[32m";
    static String RED = "\u001B[31m";
    static String CYAN = "\u001B[36m";

    public SortManager() {
        this.bubbleSort = new BubbleSort();
        this.selectionSort = new SelectionSort();
        this.insertionSort = new InsertionSort();
        this.quickSort = new QuickSort();
    }

    static void separatorLine(boolean hasColor) {
        if (hasColor) {
            System.out.println(CYAN + "- - - - - - - - - -" + RESET);
        } else {
            System.out.println("- - - - - - - - - -");
        }
    }

    static int waitUserInput(String askText) {
        Scanner scanner = new Scanner(System.in);
        int userOption = 0;

        while (userOption <= 0) {
            System.out.print(askText);
            try {
                userOption = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Opcion incorrecta. Ingrese un numero.");
                // Consumir la entrada no válida
                scanner.nextLine();
            }
        }

        return userOption;
    }

    public static int[] linkedListToArray(LinkedListManager linkedList) {
        Node current = linkedList.head;
        int size = linkedList.getSize();
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = current.value;
            current = current.next;
        }

        return array;
    }

    void runSortMenu(LinkedListManager linkedList) {
        Scanner scanner = new Scanner(System.in);
        int userOption = 0;
        boolean sortMenuInUse = true;

        // Menu y Operaciones.
        System.out.println("\n- - - - - - - - - -");
        System.out.println(GREEN + "*** Menu de Ordenamiento ***" + RESET);
        System.out.println("(1) Ordenar valores por Intercambio.");
        System.out.println("(2) Ordenar valores por Seleccion.");
        System.out.println("(3) Ordenar valores por Insercion.");
        System.out.println("(4) Ordenar valores por Shell.");
        System.out.println("(5) Ordenar valores por QuickSort (Rapida).");
        System.out.println("(6) Ordenar valores por Busqueda Secuencial.");
        System.out.println("(7) Ordenar valores por Busqueda Binaria.");
        System.out.println("(8) Imprimir todos los metodos.");
        System.out.println("(9) Regresar al menu principal.");

        userOption = waitUserInput("Opcion: ");

        // Definimos nuestra lista enlazada simple como array.
        System.out.println("\nLista inicial:");
        linkedList.displayList();

        // Obtener la lista como un array para usar en los algoritmos de ordenamiento y búsqueda.
        int[] array = linkedListToArray(linkedList);

        // Condiciones
        switch (userOption) {
            case 1:
                System.out.println();
                int[] bubbleSortArray = array.clone();
                bubbleSort(bubbleSortArray, true);
                break; // (A) Ordenamiento por Intercambio (Bubble Sort)
            case 2:
                System.out.println();
                int[] selectionSortArray = array.clone();
                selectionSort(selectionSortArray);
                break; // (B) Ordenamiento por Selección (Selection Sort)
            case 3:
                System.out.println();
                int[] insertionSortArray = array.clone();
                insertionSort(insertionSortArray);
                break; // (C) Ordenamiento por Inserción (Insertion Sort)
            case 4:
                System.out.println();
                int[] shellSortArray = array.clone();
                shellSort(shellSortArray);
                break; // (D) Ordenamiento Shell (Shell Sort)
            case 5:
                System.out.println();
                int[] quickSortArray = array.clone();
                quickSort(quickSortArray, 0, quickSortArray.length - 1);
                break; // (E) Ordenamiento Quicksort
            case 6:
                System.out.println();
                int keySequential = waitUserInput("Ingrese el valor a buscar: ");
                sequentialSearch(array, keySequential, true);
                break; // (F) Búsqueda Secuencial
            case 7:
                System.out.println();
                int keyBinary = waitUserInput("Ingrese el valor a buscar: ");
                int[] quickSortedArray = array.clone();
                binarySearch(quickSortedArray, keyBinary, true);
                break; // (G) Búsqueda Binaria
            case 8:
                System.out.println();
                separatorLine(false);
                System.out.println(GREEN + "Resultados de todos los metodos:" + RESET);
                int[] resultsArray = array.clone();
                printAllResults(resultsArray);
                break;
            case 9:
                System.out.println();
                separatorLine(false);
                System.out.println("Has salido del menu de ordenamiento.");
                separatorLine(false);
                System.out.println();
                sortMenuInUse = false;
                break;
        }

        if (sortMenuInUse) {
            runSortMenu(linkedList);
        }
    }
}

// Nota sobre la complejidad: 
// La complejidad de un algoritmo se calcula generalmente analizando su comportamiento en términos
// de la entrada (o tamaño del problema). Sin embargo, calcular la complejidad de manera dinámica 
// durante la ejecución de un programa no es una práctica común, ya que la complejidad se determina
// teóricamente y no cambia mientras el algoritmo se ejecuta. La complejidad es una característica del 
// algoritmo en sí, no del conjunto de datos específico.
