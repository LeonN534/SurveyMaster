/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package administrarEncuesta;

import encuesta.MensajeDialog;
import static encuesta.SeleccionPrograma.PASSWORD;
import static encuesta.SeleccionPrograma.URL;
import static encuesta.SeleccionPrograma.USERNAME;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.*;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;


public class PanelVerRespuesta extends JPanel{
    
    int numeroPregunta; 
    int posYNuevaOpcion = 55;
    int alturaPanel = 92;
    String texto, tipo_respuesta, opciones, encuestaID;
    JLabel numeroDePregunta;
    JLabel pregunta;
    String[] graficosLista = {"Gráfico de Barras", "Gráfico de Pastel"};
    JComboBox<String> graficosComboBox;
    JButton abrirGrafico;
    String opcionSeleccionada = "Gráfico de Barras";
    JLabel respuestaMultilinea;
    
    public PanelVerRespuesta(int numeroPregunta, String texto, String tipo_respuesta, String opciones, String encuestaID, int posYPanel, JPanel padre, String nombreEncuesta) {
        setLayout(null);

        this.numeroPregunta = numeroPregunta;
        this.texto = texto;
        this.tipo_respuesta = tipo_respuesta;
        this.opciones = opciones;
        this.encuestaID = encuestaID;
        
        numeroDePregunta = new JLabel(String.valueOf(this.numeroPregunta) + ".");
        numeroDePregunta.setBounds(17, 17, 25, 25);
        numeroDePregunta.setVisible(true);
        
        
        pregunta = new JLabel(this.texto);
        pregunta.setBounds(57, 17, 1020, 25);
        pregunta.setVisible(true);
        
        if(tipo_respuesta.equals("Texto")) {
            alturaPanel = 57;
            try (Connection conexion = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                // Consulta SQL parametrizada
                String sql = "SELECT texto_respuesta FROM respuestas WHERE encuestaID = ? AND numeroPregunta = ?";
                // Crear el PreparedStatement
                try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                    // Establecer el valor del parámetro
                    statement.setString(1, this.encuestaID);
                    statement.setString(2, String.valueOf(this.numeroPregunta));
                    
                    // Ejecutar la consulta
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            // Obtener los valores de cada columna
                            alturaPanel = alturaPanel + 35;

                            String texto_respuesta = resultSet.getString("texto_respuesta");
                            respuestaMultilinea = new JLabel("<html><body style='width: 600px;'>" + texto_respuesta + "</body></html>");
                            respuestaMultilinea.setBackground(new Color(31, 31, 31));
                            respuestaMultilinea.setOpaque(true);
                            respuestaMultilinea.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(15, 15, 15), 2, true));
                            respuestaMultilinea.setBounds(57, posYNuevaOpcion, 900, 25);
                            respuestaMultilinea.setVisible(true);
                            add(respuestaMultilinea);
                            padre.repaint();
                            padre.revalidate();
                            posYNuevaOpcion = posYNuevaOpcion + 35;

                        }
                    }
                }

            } catch (SQLException e) {
                System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            }

        } else {
            graficosComboBox = new JComboBox<>(graficosLista);
            graficosComboBox.setBounds(57, 55, 220, 25);
            graficosComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Obtener la opción seleccionada
                    opcionSeleccionada = (String) graficosComboBox.getSelectedItem();
                }
            });
            graficosComboBox.setVisible(true);
            
            abrirGrafico = new JButton("Visualizar gráfico");
            abrirGrafico.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    mostrarGrafico(opcionSeleccionada, nombreEncuesta, String.valueOf(numeroPregunta));
                }
            });
            abrirGrafico.setBounds(642, 55, 320, 25);
            abrirGrafico.setVisible(true);
            
            add(graficosComboBox);
            add(abrirGrafico);
        }
        
       
        

        
        setVisible(true);
        setBackground(new java.awt.Color(51, 51, 51));
        setBorder(null);
        setBounds(17, posYPanel, 1010, alturaPanel);
        setFocusable(true);
        setOpaque(true);
        
        add(numeroDePregunta);
        add(pregunta);
        
    }
    
    public void mostrarGrafico(String tipoGrafico, String nombreEncuesta, String numPregunta) {
        
        String[] opcSeparadas = this.opciones.split(",");
        List<String> listaDeStrings = Arrays.asList(opcSeparadas);
        // Asegúrate de tener un array de enteros con las frecuencias correspondientes para cada categoría
        List<Integer> listaDeFrecuencias = new ArrayList<>();
        
        try (Connection conexion = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            // Consulta SQL parametrizada
            String sql = "SELECT COUNT(*) as total FROM respuestas WHERE encuestaID = ? AND numeroPregunta = ? AND texto_respuesta = ?";
            // Crear el PreparedStatement
            for (String textoRespuesta : opcSeparadas) {
                // Crear el PreparedStatement
                try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                    // Establecer los valores de los parámetros
                    statement.setString(1, this.encuestaID); // Reemplaza con el valor adecuado
                    statement.setInt(2, this.numeroPregunta); // Reemplaza con el valor adecuado
                    statement.setString(3, textoRespuesta);

                    // Ejecutar la consulta
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            // Obtener el total de coincidencias y agregarlo a la lista
                            int totalCoincidencias = resultSet.getInt("total");
                            listaDeFrecuencias.add(totalCoincidencias);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }

        switch (tipoGrafico) {
            case "Gráfico de Barras":
                CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Gráfico de Barras").xAxisTitle("Opciones").yAxisTitle("Frecuencia").build();
                chart.getStyler().setXAxisLabelRotation(45); // Rotar las etiquetas para mayor claridad
                chart.addSeries("Frecuencias", listaDeStrings, listaDeFrecuencias);
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        // Create and set up the window.
                        JFrame frame = new JFrame("Ver Gráfico");
                        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        frame.setLayout(new BorderLayout());

                        // chart
                        JPanel chartPanel = new XChartPanel<CategoryChart>(chart);
                        frame.add(chartPanel, BorderLayout.CENTER);

                        // label
                        JButton button = new JButton("Descargar gráfico como imagen");
                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                try {
                                    BitmapEncoder.saveBitmapWithDPI(chart, "./" + nombreEncuesta + "-" + "Pregunta " + numPregunta + "-Gráfico de Barras", BitmapFormat.PNG, 300);
                                    MensajeDialog mensajeconf = new MensajeDialog(frame, true, "El gráfico se guardó con éxito");
                                    mensajeconf.setLocationRelativeTo(null);
                                    mensajeconf.setVisible(true);
                                } catch (IOException ex) {
                                    Logger.getLogger(PanelVerRespuesta.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        frame.add(button, BorderLayout.SOUTH);

                        // Display the window.
                        frame.pack();
                        frame.setVisible(true);
                    }
                });
                break;
                
            case "Gráfico de Pastel":
                PieChart chart2 = new PieChartBuilder().width(800).height(600).title("Gráfico de Pie").build();

                for (int i = 0; i < listaDeStrings.size(); i++) {
                    chart2.addSeries(listaDeStrings.get(i), listaDeFrecuencias.get(i));
                }
                
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        // Create and set up the window.
                        JFrame frame = new JFrame("Ver Gráfico");
                        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        frame.setLayout(new BorderLayout());

                        // chart
                        JPanel chartPanel = new XChartPanel<PieChart>(chart2);
                        frame.add(chartPanel, BorderLayout.CENTER);

                        // label
                        JButton button = new JButton("Descargar gráfico como imagen");
                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                try {
                                    BitmapEncoder.saveBitmapWithDPI(chart2, "./" + nombreEncuesta + "-" + "Pregunta " + numPregunta + "-Gráfico de Pie", BitmapFormat.PNG, 300);
                                    MensajeDialog mensajeconf = new MensajeDialog(frame, true, "El gráfico se guardó con éxito");
                                    mensajeconf.setLocationRelativeTo(null);
                                    mensajeconf.setVisible(true);
                                } catch (IOException ex) {
                                    Logger.getLogger(PanelVerRespuesta.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        frame.add(button, BorderLayout.SOUTH);

                        // Display the window.
                        frame.pack();
                        frame.setVisible(true);
                    }
                });
                break;
            default:
                throw new AssertionError();
        }
        
    }
    
}
