package linkedlistsorting;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

public class SortViewer {
    // Variables Generales

    LinkedListManager linkedList;
    private SortAdvancedViewer advancedViewer;

    // Variables de Interfaz

    JFrame sortFrame;
    private JRadioButton manualData;
    private JRadioButton autoData;
    private JTextField insertValueField;
    private JComboBox randomValueQuantity;
    private JButton insertValueButton;
    private JButton bubbleSortButton;
    private JButton quickSortButton;
    private JButton shellSortButton;
    private JButton selectionSortButton;
    private JButton insertionSortButton;
    private JLabel listSizeLabel;
    public JPanel sortMenuPanel;
    private JButton generateValueButton;
    private JButton sequentialSearchButton;
    private JButton binarySearchButton;
    private JButton showAdvancedButton;
    private JButton openGithubButton;
    private JButton openCreditsButton;

    public SortViewer() {
    }

    // Constructores y Listeners

    public SortViewer(LinkedListManager linkedList) {
        this.linkedList = linkedList;

        // Form initialization.

        this.randomValueQuantity.addItem(100);
        this.randomValueQuantity.addItem(1000);
        this.randomValueQuantity.addItem(10000);
        this.randomValueQuantity.addItem(100000);
        setManualInput(true);

        // Initializes Frame

        // Initialize the main frame
        sortFrame = new JFrame("Insightful Sorts");
        JScrollPane scrollSortPane = new JScrollPane(sortMenuPanel);
        scrollSortPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollSortPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sortFrame.setContentPane(scrollSortPane);
        sortFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create and add the showAdvancedButton
        showAdvancedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAdvancedSortView();
            }
        });

        // Show the main frame
        sortFrame.pack();
        sortFrame.setVisible(true);

        // Listeners

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

        // Botones de Ordenamiento

        bubbleSortButton.addActionListener(e -> showSortResults("Bubble Sort", () -> BubbleSort.bubbleSort(SortManager.linkedListToArray(linkedList), false)));
        quickSortButton.addActionListener(e -> showSortResults("QuickSort", () -> {
            int[] array = SortManager.linkedListToArray(linkedList);
            QuickSort.quickSort(array, 0, array.length - 1);
        }));
        shellSortButton.addActionListener(e -> showSortResults("Shell Sort", () -> ShellSort.shellSort(SortManager.linkedListToArray(linkedList))));
        selectionSortButton.addActionListener(e -> showSortResults("Selection Sort", () -> SelectionSort.selectionSort(SortManager.linkedListToArray(linkedList))));
        insertionSortButton.addActionListener(e -> showSortResults("Insertion Sort", () -> InsertionSort.insertionSort(SortManager.linkedListToArray(linkedList))));

        // Botones de Busqueda

        sequentialSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSearchWindow("Busqueda Secuencial", false);
            }
        });

        binarySearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSearchWindow("Busqueda Binaria", true);
            }
        });

        // Extra buttons.

        openGithubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre el enlace al hacer clic en el botón
                openLink("https://github.com/joeltorres-7/insightful-sorts");
            }
        });

        openCreditsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCreditsView();
            }
        });
    }

    public JFrame getSortFrame() {
        return sortFrame;
    }

    private void showAdvancedSortView() {
        // Hide the current frame
        sortFrame.setVisible(false);

        // Create and show the AdvancedSortViewer using the existing linkedList
        advancedViewer = new SortAdvancedViewer(linkedList, this);
    }

    // Metodos Legacy

    // Manipulacion de LinkedList

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

    // Ordenamiento

    private void showSortResults(String methodName, Runnable sortingMethod) {
        // Crear la ventana de resultados
        JFrame resultFrame = new JFrame("Resultados | " + methodName);
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Ejecutar el método de ordenamiento
        long startTime = System.nanoTime();
        sortingMethod.run();
        long endTime = System.nanoTime();

        // Obtener el array ordenado
        int[] sortedArray = SortManager.linkedListToArray(linkedList);

        // Ordenar el array
        Arrays.sort(sortedArray);

        // Crear modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Indice");
        model.addColumn("Valor");

        // Agregar datos a la tabla (máximo 100 elementos)
        for (int i = 0; i < Math.min(sortedArray.length, 100); i++) {
            model.addRow(new Object[]{i, sortedArray[i]});
        }

        // Crear tabla con el modelo
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Crear el panel de resultados
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Crear etiquetas con información del método y tiempo de ejecución
        JLabel methodLabel = new JLabel("Metodo de Ordenamiento: " + methodName);
        JLabel timeLabel = new JLabel("Tiempo de Ejecucion: " + (endTime - startTime) + " nanosegundos");

        // Ajustar la apariencia de las etiquetas
        methodLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Agregar márgenes y padding
        methodLabel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        timeLabel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // Agregar las etiquetas al panel de resultados
        resultPanel.add(methodLabel, BorderLayout.NORTH);
        resultPanel.add(timeLabel, BorderLayout.SOUTH);

        // Agregar el panel de resultados al marco y mostrarlo
        resultFrame.getContentPane().add(resultPanel);
        resultFrame.pack();
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setVisible(true);
    }

    // Abrir enlace a repositorio.

    private void openLink(String link) {
        try {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(new URI(link));
            } else {
                JOptionPane.showMessageDialog(null, "La apertura del enlace no es compatible en este sistema.");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al abrir el enlace: " + ioException.getMessage());
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error de sintaxis de URI: " + uriSyntaxException.getMessage());
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error inesperado: " + exception.getMessage());
        }
    }

    // Metodos de Busqueda

    // Método para abrir la ventana de búsqueda
    private void openSearchWindow(String algorithm, boolean useBinarySearch) {
        JFrame searchFrame = new JFrame("Parametros de Busqueda");
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear un campo de texto para ingresar el número a buscar
        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Buscar");

        // Establecer la fuente para el campo de texto
        searchField.setFont(new Font("Open Sans", Font.PLAIN, 12));

        // Establecer la fuente y el estilo para el botón de búsqueda
        searchButton.setFont(new Font("Open Sans", Font.BOLD, 12));
        searchButton.setBackground(new Color(5, 112, 220));
        searchButton.setForeground(Color.WHITE);

        // Crear el panel de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Valor a buscar:"));
        searchPanel.add(searchField);
        searchPanel.add(Box.createVerticalStrut(8));  // Espacio vertical
        searchPanel.add(searchButton);

        // Agregar márgenes y padding al panel de búsqueda
        searchPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        // Agregar el panel de búsqueda al marco y mostrarlo
        searchFrame.getContentPane().add(searchPanel);
        searchFrame.pack();
        searchFrame.setLocationRelativeTo(null);
        searchFrame.setVisible(true);

        // ActionListener para el botón de búsqueda
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el valor ingresado
                int searchValue = Integer.parseInt(searchField.getText());

                // Medir el tiempo de ejecución
                long startTime = System.nanoTime();

                // Ejecutar la búsqueda
                int position = useBinarySearch ?
                        BinarySearch.binarySearch(SortManager.linkedListToArray(linkedList), searchValue, false) :
                        SequentialSearch.sequentialSearch(SortManager.linkedListToArray(linkedList), searchValue, false);

                // Calcular el tiempo de ejecución
                long endTime = System.nanoTime();
                long executionTime = endTime - startTime;

                // Mostrar resultados
                showSearchResults(algorithm, position, executionTime);
                searchFrame.dispose();  // Cerrar la ventana de búsqueda después de mostrar resultados
            }
        });
    }

    private void showSearchResults(String algorithm, int position, long executionTime) {
        JFrame resultFrame = new JFrame("Resultados de Busqueda");
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear el panel de resultados
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.add(new JLabel("Algoritmo: " + algorithm));
        resultPanel.add(new JLabel("Posicion: " + (position != -1 ? position : "No encontrado")));
        resultPanel.add(new JLabel("Tiempo de Ejecucion: " + executionTime + " nanosegundos"));

        // Establecer la fuente para los componentes del panel de resultados
        Font resultFont = new Font("Open Sans", Font.PLAIN, 12);
        Arrays.asList(resultPanel.getComponents()).forEach(comp -> comp.setFont(resultFont));

        // Agregar márgenes y padding al panel de resultados
        resultPanel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        // Agregar el panel de resultados al marco y mostrarlo
        resultFrame.getContentPane().add(resultPanel);
        resultFrame.pack();
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setVisible(true);
    }

    // Credits

    private void showCreditsView() {
        JFrame creditsFrame = new JFrame("Créditos");
        creditsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        creditsFrame.setSize(400, 300);

        // Crear un JLabel para la imagen con padding
        JLabel catLabel = createImageLabel("https://img.icons8.com/pastel-glyph/64/FFFFFF/cat--v3.png");
        catLabel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0)); // Padding

        // Crear JLabels para la información de créditos
        JLabel titleLabel = createLabel("Créditos", Font.BOLD, 24);
        JLabel developerLabel = createLabel("Desarrollador: Joel A. Torres", Font.PLAIN, 16);
        JLabel versionLabel = createLabel("Versión: 1.0", Font.PLAIN, 16);
        JLabel yearLabel = createLabel("Año: 2023", Font.PLAIN, 16);

        // Crear un panel para organizar los JLabels con márgenes y relleno
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Márgenes
        contentPanel.add(Box.createVerticalGlue()); // Espacio en la parte superior
        contentPanel.add(titleLabel);
        contentPanel.add(catLabel);
        contentPanel.add(developerLabel);
        contentPanel.add(versionLabel);
        contentPanel.add(yearLabel);
        contentPanel.add(Box.createVerticalGlue()); // Espacio en la parte inferior

        // Configurar la fuente Open Sans
        Font openSansFont = new Font("Open Sans", Font.PLAIN, 16);
        titleLabel.setFont(new Font("Open Sans", Font.BOLD, 24));
        developerLabel.setFont(openSansFont);
        versionLabel.setFont(openSansFont);
        yearLabel.setFont(openSansFont);

        // Centrar el contenido en la ventana
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Agregar el panel al contenido de la ventana
        creditsFrame.getContentPane().add(contentPanel);

        // Centrar la ventana en la pantalla
        creditsFrame.setLocationRelativeTo(sortFrame);
        creditsFrame.setVisible(true);
    }

    private JLabel createLabel(String text, int style, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Open Sans", style, fontSize));
        label.setForeground(Color.WHITE); // Cambia el color del texto según tu tema oscuro
        return label;
    }

    private JLabel createImageLabel(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            ImageIcon icon = new ImageIcon(url);
            JLabel label = new JLabel(icon);
            return label;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar la imagen: " + e.getMessage());
            return new JLabel(); // Devuelve un JLabel vacío en caso de error
        }
    }
}
