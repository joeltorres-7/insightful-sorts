/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linkedlistsorting;

public class LinkedListManager {
    Node head;

    // Color variables.
    String RESET = "\u001B[0m";
    String GREEN = "\u001B[32m";

    public void displayList() {
        Node current = head;
        System.out.println("\n- - - - - - - - - -" + GREEN);
        while (current != null) {
            System.out.print(current.value + " -> ");
            current = current.next;
        }
        System.out.println(RESET + "null");
        System.out.println("- - - - - - - - - -");
    }

    public void addNode(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void removeNode(int value) {
        Node current = head;
        Node prev = null;

        // Caso especial: el nodo a eliminar es el primero
        if (current != null && current.value == value) {
            head = current.next;
            return;
        }

        // Buscar el nodo a eliminar
        while (current != null && current.value != value) {
            prev = current;
            current = current.next;
        }

        // Si no se encuentra el nodo
        if (current == null) {
            System.out.println("Elemento " + value + " no encontrado en la lista.");
            return;
        }

        // Eliminar el nodo
        prev.next = current.next;
    }

    public int getSize() {
        int count = 0;
        Node current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    void clearList() {
        head = null;
    }
}
