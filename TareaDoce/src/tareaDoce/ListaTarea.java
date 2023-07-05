package tareaDoce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ListaTarea extends JFrame {

	private DefaultListModel<String> modeloLista;
    private JList<String> listaTareas;
    private JTextField textFieldTarea;
    private JButton botonAgregar;
    private JButton botonCompletar;
    private JButton botonEliminar;

    public ListaTarea() {
        setTitle("Lista de Tareas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

       
        modeloLista = new DefaultListModel<>(); // Crear el modelo de la lista

        
        listaTareas = new JList<>(modeloLista);// Crear la lista de tareas
        add(new JScrollPane(listaTareas), BorderLayout.CENTER);

        
        JPanel panelTareas = new JPanel(new BorderLayout());// Crear el panel para ingresar tareas

        
        textFieldTarea = new JTextField();
        panelTareas.add(textFieldTarea, BorderLayout.CENTER);// Crear el campo de texto para ingresar tareas

        
        botonAgregar = new JButton("Agregar");// Crear el botón para agregar tareas
        botonAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarTarea();
            }
        });
        panelTareas.add(botonAgregar, BorderLayout.EAST);

        
        botonCompletar = new JButton("Completar");// Crear el botón para completar tareas
        botonCompletar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                completarTarea();
            }
        });
        panelTareas.add(botonCompletar, BorderLayout.WEST);

        
        botonEliminar = new JButton("Eliminar");// Crear el botón para eliminar tareas
        botonEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarTarea();
            }
        });
        panelTareas.add(botonEliminar, BorderLayout.SOUTH);

        add(panelTareas, BorderLayout.SOUTH);
    }

    private void agregarTarea() {
        String tarea = textFieldTarea.getText().trim();
        if (!tarea.isEmpty()) {
            modeloLista.addElement(tarea);
            textFieldTarea.setText("");
        }
    }

    private void completarTarea() {
        int indice = listaTareas.getSelectedIndex();
        if (indice >= 0) {
            String tarea = modeloLista.getElementAt(indice);
            modeloLista.setElementAt("[COMPLETADA] " + tarea, indice);
        }
    }

    private void eliminarTarea() {
        int indice = listaTareas.getSelectedIndex();
        if (indice >= 0) {
            modeloLista.remove(indice);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {	
                ListaTarea app = new ListaTarea();
                app.setVisible(true);
            }
        });
    }
}
