package linkedlistsorting;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class SortAdvancedViewer {
    // Variables Generales

    LinkedListManager linkedList;
    private SortViewer sortViewer;
    OutputHandler outputHandler = new OutputHandler();

    // Variables de Interfaz

    JFrame advancedSortFrame;
    public JPanel advancedSortMenuPanel;
    private JRadioButton manualData;
    private JTextField insertValueField;
    private JButton insertValueButton;
    private JRadioButton autoData;
    private JComboBox randomValueQuantity;
    private JButton generateValueButton;
    private JButton showSortGraphButton;
    private JButton showSearchGraphButton;
    private JButton backToSimpleButton;
    private JLabel listSizeLabel;
    private JTextField searchValueField;
    private JButton showListButton;

    public SortAdvancedViewer(LinkedListManager linkedList, SortViewer sortViewer) {
        this.linkedList = linkedList;
        this.sortViewer = sortViewer;

        // Form initialization.

        this.randomValueQuantity.addItem(100);
        this.randomValueQuantity.addItem(1000);
        this.randomValueQuantity.addItem(10000);
        this.randomValueQuantity.addItem(100000);
        setManualInput(true);
        listSizeLabel.setText(String.valueOf(linkedList.getSize()));

        // Initialize the main frame
        advancedSortFrame = new JFrame("Insightful Sorts | Avanzado");
        JScrollPane scrollAdvancedSortPane = new JScrollPane(advancedSortMenuPanel);
        scrollAdvancedSortPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollAdvancedSortPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        advancedSortFrame.setContentPane(scrollAdvancedSortPane);
        advancedSortFrame.setContentPane(advancedSortMenuPanel);
        advancedSortFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create and add the backToSimpleButton
        backToSimpleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSortFrame();
            }
        });

        // Graficas

        // Create and add the showSortGraphButton
        showSortGraphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSortGraph();
            }
        });

        showListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTable();
            }
        });

        // Create and add the showSearchGraphButton
        showSearchGraphButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int searchValue = Integer.parseInt(searchValueField.getText());
                    int[] array = SortManager.linkedListToArray(linkedList);
                    showSearchGraph(searchValue);

                    if (SequentialSearch.sequentialSearch(array, searchValue, false) != -1) {
                        JOptionPane.showMessageDialog(null, "Elemento encontrado.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Elemento no encontrado.");
                    }
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "Solo numeros positivos.");
                }
            }
        });

        // Insercion de Datos

        insertValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fieldValue;

                try {
                    fieldValue = Integer.parseInt(insertValueField.getText());
                    addToList(fieldValue);

                    // Updates list size ui value.

                    listSizeLabel.setText(String.valueOf(linkedList.getSize()));
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, "Solo numeros positivos.");
                }
            }
        });

        generateValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dataQuantity = Integer.parseInt(String.valueOf(randomValueQuantity.getSelectedItem()));
                addRandomList(dataQuantity);
            }
        });

        manualData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setManualInput(true);
            }
        });

        autoData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setManualInput(false);
            }
        });

        // Show the main frame
        advancedSortFrame.pack();
        advancedSortFrame.setVisible(true);
    }

    private void showSortFrame() {
        // Show the existing SortViewer frame
        sortViewer.getSortFrame().setVisible(true);

        // Dispose of the current AdvancedSortFrame
        advancedSortFrame.dispose();
    }

    private void addToList(int userValue) {
        try {
            linkedList.addNode(userValue);
            JOptionPane.showMessageDialog(null, "Elemento agregado con exito.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Recuerde, solo numeros positivos.");
        }
    }

    private void addRandomList(int length) {
        linkedList.clearList();  // Clear the current list

        // Generate and add random numbers to the list
        for (int i = 0; i < length; i++) {
            int randomValue = (int) (Math.random() * 1000);  // Adjust the range as needed
            linkedList.addNode(randomValue);
        }

        // Update list size ui value if needed.
        listSizeLabel.setText(String.valueOf(linkedList.getSize()));

        // Success

        JOptionPane.showMessageDialog(null, "Lista aleatoria de tamaño " + length + " generada con exito.");
    }

    private void setManualInput(boolean manualInput) {
        if (manualInput) {
            this.autoData.setSelected(false);
            this.manualData.setSelected(true);
            this.insertValueField.setEnabled(true);
            this.insertValueButton.setEnabled(true);
            this.randomValueQuantity.setEnabled(false);
            this.generateValueButton.setEnabled(false);
        } else {
            this.autoData.setSelected(true);
            this.manualData.setSelected(false);
            this.insertValueField.setEnabled(false);
            this.insertValueButton.setEnabled(false);
            this.randomValueQuantity.setEnabled(true);
            this.generateValueButton.setEnabled(true);
        }
    }

    // Graficas

    private void showSortGraph() {
        int[] array = SortManager.linkedListToArray(linkedList);
        DefaultCategoryDataset dataset = createSortDataset(array);
        JFreeChart chart = createBarChart("Tiempo de Ordenamiento", "Algoritmo", "Tiempo (nanosegundos)", dataset);
        showChart(chart, "Grafica de Tiempo de Ordenamiento");
    }

    private void showSearchGraph(int searchKey) {
        int[] array = SortManager.linkedListToArray(linkedList);
        JFreeChart chart = createBarChart("Tiempo de Busqueda", "Algoritmo", "Tiempo (nanosegundos)", createSearchDataset(array, searchKey));
        showChart(chart, "Grafica de Tiempo de Busqueda");
    }

    private static JFreeChart createBarChart(String chartTitle, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                chartTitle,
                categoryAxisLabel,
                valueAxisLabel,
                dataset
        );

        // Customize the appearance of the chart
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", NumberFormat.getInstance()));
        renderer.setBaseItemLabelsVisible(true);

        return chart;
    }

    private DefaultCategoryDataset createSortDataset(int[] array) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        addSortingMethodToDataset(dataset, "Bubble Sort", array.clone());
        addSortingMethodToDataset(dataset, "Selection Sort", array.clone());
        addSortingMethodToDataset(dataset, "Insertion Sort", array.clone());
        addSortingMethodToDataset(dataset, "Shell Sort", array.clone());
        addSortingMethodToDataset(dataset, "QuickSort", array.clone());

        return dataset;
    }

    private void addSortingMethodToDataset(DefaultCategoryDataset dataset, String methodName, int[] array) {
        long executionTime = OutputHandler.getExecutionTime(array);
        dataset.addValue(executionTime, methodName, "Tiempo de Ejecucion");
    }

    private static CategoryDataset createSearchDataset(int[] array, int key) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(OutputHandler.getSearchExecutionTime(array, key, array, false), "Busqueda Secuencial", "Tiempo de Ejecucion");
        dataset.addValue(OutputHandler.getSearchExecutionTime(array, key, array, true), "Busqueda Binaria", "Tiempo de Ejecucion");
        return dataset;
    }

    private static void showChart(JFreeChart chart, String frameTitle) {
        JFrame frame = new JFrame(frameTitle);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new ChartPanel(chart));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Visualizacion de Lista

    private void showTable() {
        int[] array = SortManager.linkedListToArray(linkedList);

        JFrame frame = new JFrame("Lista Actual");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear modelo de tabla
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Índice");
        tableModel.addColumn("Valor");

        // Agregar datos al modelo
        int maxRows = Math.min(100, array.length);
        for (int i = 0; i < maxRows; i++) {
            tableModel.addRow(new Object[]{i, array[i]});
        }

        // Crear tabla con el modelo
        JTable table = new JTable(tableModel);

        // Agregar tabla a un JScrollPane para que sea desplazable si hay muchos datos
        JScrollPane scrollPane = new JScrollPane(table);

        // Agregar JScrollPane al frame
        frame.getContentPane().add(scrollPane);

        // Hacer visible el frame
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
