/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package creacionEncuesta;

import static creacionEncuesta.Crearencuesta.preguntas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelPregunta extends JPanel{
        
    ArrayList<JLabel> vinetas = new ArrayList<>();
    ArrayList<JTextField> opciones = new ArrayList<>();
    int posYBotonAdd = 55;
    int posYBotonBorr = 20;
    int posYNuevaOpcion = 55;
    int alturaPanel = 92;
    JButton eliminarPregunta = new JButton("Eliminar pregunta");
    int index;
    JLabel numeroPregunta;
    JTextField pregunta = new JTextField("Pregunta sin título");
    String[] items = {"Texto", "Opción múltiple"};

    JComboBox<String> comboBoxTiposPregunta = new JComboBox<>(items);


    
    public PanelPregunta(int posYPanel, int index) {
        setLayout(null);
        this.index = index;
        
        JButton añadirOpcion = new JButton("+");
        JButton borrarOpcion = new JButton("-");

        numeroPregunta = new JLabel(String.valueOf(this.index + 1));
        numeroPregunta.setBounds(17, 17, 25, 25);
        numeroPregunta.setVisible(true);
        
        pregunta.setBounds(57, 17, 442, 25);
        pregunta.setVisible(true);

        JLabel tipoPreguntaLabel = new JLabel("Tipo de pregunta:");
        tipoPreguntaLabel.setBounds(642, 19, 120, 25);
        tipoPreguntaLabel.setVisible(true);
        
        JLabel vinetaInicial = new JLabel("•");
        vinetaInicial.setBounds(76, posYNuevaOpcion + 3, 5, 19);
        vinetaInicial.setEnabled(false);
        vinetaInicial.setVisible(true);
        
        JTextField opcionInicial = new JTextField("Opción");
        opcionInicial.setBounds(87, posYNuevaOpcion, 372, 25);
        opcionInicial.setEnabled(false);
        opcionInicial.setVisible(true);
        
        borrarOpcion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                posYNuevaOpcion = posYNuevaOpcion - 35;
                posYBotonAdd = posYBotonAdd - 35;
                posYBotonBorr = posYBotonBorr - 35;
                alturaPanel = alturaPanel - 35;
                
                borrarOpcion.setBounds(471, posYBotonBorr, 25, 25);

                if(posYBotonBorr == 20) {
                    borrarOpcion.setEnabled(false);
                    borrarOpcion.setVisible(false);
                }
                
                remove(vinetas.get(vinetas.size() - 1));
                remove(opciones.get(opciones.size() - 1));
                
                vinetas.remove(vinetas.size() - 1);
                opciones.remove(opciones.size() - 1);
                
                setBounds(17, getY(), 1020, alturaPanel);

                añadirOpcion.setBounds(471, posYBotonAdd, 25, 25);

                getComponentePrimerAnterior(getParent()).setBounds(
                        17, 
                        getComponentePrimerAnterior(getParent()).getY() - 35, 
                        1020, 
                        getComponentePrimerAnterior(getParent()).getHeight());
                
                for(Component componente : obtenerComponentesPosteriores(PanelPregunta.this, getParent())) {
                    componente.setBounds(17, componente.getY() - 35, 1020, componente.getHeight());
                }
                
                getParent().setPreferredSize(new Dimension(155, getParent().getPreferredSize().height - 35));
                
                repaint();
                revalidate();
                
                
            }
        });
        borrarOpcion.setEnabled(false);
        borrarOpcion.setVisible(false);
        
        añadirOpcion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                posYNuevaOpcion = posYNuevaOpcion + 35;
                posYBotonAdd = posYBotonAdd + 35;
                posYBotonBorr = posYBotonBorr + 35;
                alturaPanel = alturaPanel + 35;
                
                JLabel vineta = new JLabel("•");
                vineta.setBounds(76, posYNuevaOpcion + 3, 5, 19);
                vineta.setEnabled(true);
                vineta.setVisible(true);

                JTextField opcion = new JTextField("Opción");
                opcion.setBounds(87, posYNuevaOpcion, 372, 25);
                opcion.setVisible(true);
                
                setBounds(17, getY(), 1020, alturaPanel);
                
                vinetas.add(vineta);
                opciones.add(opcion);
                
                add(vineta);
                add(opcion);
                
                añadirOpcion.setBounds(471, posYBotonAdd, 25, 25);
                
                borrarOpcion.setBounds(471, posYBotonBorr, 25, 25);
                borrarOpcion.setEnabled(true);
                borrarOpcion.setVisible(true);

                getComponentePrimerAnterior(getParent()).setBounds(
                        17, 
                        getComponentePrimerAnterior(getParent()).getY() + 35, 
                        1020, 
                        getComponentePrimerAnterior(getParent()).getHeight());
                
                for(Component componente : obtenerComponentesPosteriores(PanelPregunta.this, getParent())) {
                    componente.setBounds(17, componente.getY() + 35, 1020, componente.getHeight());
                }
                
                getParent().setPreferredSize(new Dimension(155, getParent().getPreferredSize().height + 35));
                repaint();
                revalidate();
            }
            
        });
        añadirOpcion.setBounds(471, posYBotonAdd, 25, 25);
        añadirOpcion.setEnabled(false);
        añadirOpcion.setVisible(true);
     
        comboBoxTiposPregunta.setBounds(783, 17, 220, 25);
        comboBoxTiposPregunta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la opción seleccionada
                String opcionSeleccionada = (String) comboBoxTiposPregunta.getSelectedItem();
                // Verificar la opción seleccionada e imprimir en consecuencia
                if ("Opción múltiple".equals(opcionSeleccionada)) {
                    añadirOpcion.setEnabled(true);
                    borrarOpcion.setEnabled(true);
                    for(JLabel vineta : vinetas) {
                        vineta.setEnabled(true);
                    }
                    for(JTextField opcion : opciones) {
                        opcion.setEnabled(true);
                    }
                } else {
                    añadirOpcion.setEnabled(false);
                    borrarOpcion.setEnabled(false);
                    for(JLabel vineta : vinetas) {
                        vineta.setEnabled(false);
                    }
                    for(JTextField opcion : opciones) {
                        opcion.setEnabled(false);
                    }
                }
            }
        });
        comboBoxTiposPregunta.setVisible(true);
        
        eliminarPregunta.setBounds(642, 55, 361, 25);
        eliminarPregunta.setBackground(new Color(165, 29, 45));
        if(preguntas.size() < 2) {
            eliminarPregunta.setEnabled(false);
        } else {
            eliminarPregunta.setEnabled(true);
        }
        eliminarPregunta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                int posYaQuitar = 15 + getHeight();
                JPanel panelTemporal = (JPanel) getParent();
                for(Component componente : obtenerComponentesPosteriores(PanelPregunta.this, panelTemporal)) {
                    componente.setBounds(17, componente.getY() - posYaQuitar, 1020, componente.getHeight());
                }
                getComponentePrimerAnterior(getParent()).setBounds(
                        17, 
                        getComponentePrimerAnterior(panelTemporal).getY() - (15 + getHeight()), 
                        1020, 
                        getComponentePrimerAnterior(panelTemporal).getHeight());
                getParent().remove(preguntas.get(PanelPregunta.this.index));
                preguntas.remove(PanelPregunta.this.index);
                for (int i = 0; i < preguntas.size(); i++) {
                    
                    preguntas.get(i).actualizarEstadoDelBoton();
                    preguntas.get(i).index = i;
                    preguntas.get(i).numeroPregunta.setText(String.valueOf(preguntas.get(i).index + 1));
                }
                
                panelTemporal.setPreferredSize(new Dimension(155, panelTemporal.getPreferredSize().height - posYaQuitar));

                panelTemporal.repaint();
                panelTemporal.revalidate();
            }
        });
        
        eliminarPregunta.setVisible(true);
        
        setVisible(true);
        setBackground(new java.awt.Color(51, 51, 51));
        setBorder(null);
        setBounds(17, posYPanel, 1020, alturaPanel);
        setFocusable(true);
        setOpaque(true);
        add(numeroPregunta);
        add(pregunta);
        add(tipoPreguntaLabel);
        add(eliminarPregunta);
        add(comboBoxTiposPregunta);
        add(vinetaInicial);
        add(opcionInicial);
        add(añadirOpcion);
        add(borrarOpcion);
        
        vinetas.add(vinetaInicial);
        opciones.add(opcionInicial);
    }
    
    private static Component getComponentePrimerAnterior(Container parent) {
        Component[] components = parent.getComponents();

        if (components.length > 0) {
            // Devuelve el primer componente anterior
            return components[0];
        } else {
            // Si no hay componentes hijos, devuelve null
            return null;
        }
    }
    
    private static ArrayList<Component> obtenerComponentesPosteriores(Component componente, Container contenedor) {
        Component[] componentes = contenedor.getComponents();
        ArrayList<Component> componentesPosteriores = new ArrayList<>();
        boolean empezarRecorrido = false;

        for (Component c : componentes) {
            if (c == componente) {
                empezarRecorrido = true;
                continue;
            }

            if (empezarRecorrido) {
                // Agrega el componente posterior a la lista
                componentesPosteriores.add(c);
            }
        }

        return componentesPosteriores;
    }
    
    void actualizarEstadoDelBoton() {
        if(preguntas.size() < 2) {
            eliminarPregunta.setEnabled(false);
        } else {
            eliminarPregunta.setEnabled(true);
        }
    }
    


}
