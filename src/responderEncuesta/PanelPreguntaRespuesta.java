/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responderEncuesta;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PanelPreguntaRespuesta extends JPanel{
    
    int numeroPregunta; 
    int posYNuevaOpcion = 55;
    int alturaPanel = 92;
    
    String texto, tipo_respuesta, opciones;
    JTextField campoRespuesta;
    ButtonGroup buttonGroup;
    ArrayList<JRadioButton> opcionesArray = new ArrayList<>();
    
    JLabel numeroDePregunta;
    JLabel pregunta;
    
    public PanelPreguntaRespuesta(int numeroPregunta, String texto, String tipo_respuesta, String opciones, int posYPanel) {
        setLayout(null);

        this.numeroPregunta = numeroPregunta;
        this.texto = texto;
        this.tipo_respuesta = tipo_respuesta;
        this.opciones = opciones;
        
        numeroDePregunta = new JLabel(String.valueOf(this.numeroPregunta) + ".");
        numeroDePregunta.setBounds(17, 17, 25, 25);
        numeroDePregunta.setVisible(true);
        
        
        pregunta = new JLabel(this.texto);
        pregunta.setBounds(57, 17, 1020, 25);
        pregunta.setVisible(true);
        
        if(this.tipo_respuesta.equals("Texto")) {
            campoRespuesta = new JTextField("Respuesta");
            campoRespuesta.setVisible(true);
            campoRespuesta.setBounds(57, posYNuevaOpcion, 900, 25);
            add(campoRespuesta);
        } else {
            alturaPanel = 57;
            buttonGroup = new ButtonGroup();
            String[] opcionesSeparadas = this.opciones.split(",");
            for (int i = 0; i < opcionesSeparadas.length; i++) {
                JRadioButton opcion;
                if(i == 0) {
                    opcion = new JRadioButton(opcionesSeparadas[i], true);
                } else {
                    opcion = new JRadioButton(opcionesSeparadas[i], false);
                }
                
                alturaPanel = alturaPanel + 35;
                opcionesArray.add(opcion);
                buttonGroup.add(opcion);
                opcion.setBounds(57, posYNuevaOpcion, 500, 25);
                opcion.setVisible(true);
                add(opcion);
                posYNuevaOpcion = posYNuevaOpcion + 35;

            }
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
}
