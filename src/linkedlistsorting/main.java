/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package linkedlistsorting;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import static linkedlistsorting.OutputHandler.runHomeMenu;

public class main {
    static void separatorLine() {
        System.out.println("- - - - - - - - - -");
    }

    // Menu

    public static void main(String[] args) {
        String RESET = "\u001B[0m";
        String CYAN = "\u001B[36m";

        // Inicializa la lista de polinomios.

        LinkedListManager binaryTreeManager = new LinkedListManager();
        SortManager sortManager = new SortManager();

        // Codigo de Operaciones

        System.out.println(CYAN + "Nombre del Proyecto: Ordenamiento en Listas Enlazadas Simples" + RESET);
        separatorLine();
        System.out.println(CYAN + "Integrantes:" + RESET +
                "\n* Jerley Saieth Hernandez Calvo" +
                "\n* Nelmer Daniel Roa Cardenas" +
                "\n* Joel Alejandro Torres Canas"
        );
        separatorLine();

        // Define Look and Feel.

        SwingUtilities.invokeLater(() -> {
            try {
                // Set Substance look and feel on the EDT
                UIManager.setLookAndFeel(new FlatMacDarkLaf());

                // Your Swing application code here...
                new SortViewer(binaryTreeManager);
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        });

        runHomeMenu(binaryTreeManager, sortManager);
    }
}