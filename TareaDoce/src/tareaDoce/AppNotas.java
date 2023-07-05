package tareaDoce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class AppNotas extends JFrame {


	private JTextArea areaTexto;
    private JList<String> listaNotas;
    private DefaultListModel<String> modeloLista;
    private List<String> notas;

    public AppNotas() {
        setTitle("Aplicación de Notas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior con el área de texto para editar la nota
        areaTexto = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior con botones y la lista de notas
        JPanel panelInferior = new JPanel(new BorderLayout());

        // Botones de acciones
        JButton btnNuevaNota = new JButton("Nueva Nota");
        JButton btnGuardarNota = new JButton("Guardar Nota");
        JButton btnEliminarNota = new JButton("Eliminar Nota");
        JButton btnModificarNota = new JButton("Modificar Nota");

        // Acciones de los botones
        btnNuevaNota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                areaTexto.setText("");
            }
        });

        btnGuardarNota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarNota();
            }
        });

        btnEliminarNota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarNota();
            }
        });
        
        btnModificarNota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarNota();
            }
        });

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnNuevaNota);
        panelBotones.add(btnGuardarNota);
        panelBotones.add(btnEliminarNota);
        panelBotones.add(btnModificarNota);

        panelInferior.add(panelBotones, BorderLayout.NORTH);

        // Lista de notas
        modeloLista = new DefaultListModel<>();
        listaNotas = new JList<>(modeloLista);
        JScrollPane scrollLista = new JScrollPane(listaNotas);
        panelInferior.add(scrollLista, BorderLayout.CENTER);

        add(panelInferior, BorderLayout.SOUTH);

        // Cargar las notas existentes
        cargarNotas();

        // Actualizar la lista de notas
        actualizarListaNotas();

        // Mostrar la primera nota si hay alguna
        if (!notas.isEmpty()) {
            listaNotas.setSelectedIndex(0);
            mostrarNotaSeleccionada();
        }

        // Listener de selección de notas en la lista
        listaNotas.addListSelectionListener(e -> mostrarNotaSeleccionada());
    }

    private void cargarNotas() {
        notas = new ArrayList<>();
        try {
            File archivo = new File("notas.txt");
            if (archivo.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = br.readLine()) != null) {
                    notas.add(linea);
                }
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarNotas() {
        try {
            File archivo = new File("notas.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            for (String nota : notas) {
                bw.write(nota);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actualizarListaNotas() {
        modeloLista.clear();
        for (String nota : notas) {
            modeloLista.addElement(nota);
        }
    }

    private void guardarNota() {
        String nota = areaTexto.getText();
        notas.add(nota);
        guardarNotas();
        actualizarListaNotas();
    }

    private void eliminarNota() {
        int indiceSeleccionado = listaNotas.getSelectedIndex();
        if (indiceSeleccionado != -1) {
            notas.remove(indiceSeleccionado);
            guardarNotas();
            actualizarListaNotas();
            areaTexto.setText("");
        }
    }

    private void modificarNota() {
        int indiceSeleccionado = listaNotas.getSelectedIndex();
        if (indiceSeleccionado != -1) {
            String nota = areaTexto.getText();
            notas.set(indiceSeleccionado, nota);
            guardarNotas();
            actualizarListaNotas();
        }
    }

    private void mostrarNotaSeleccionada() {
        int indiceSeleccionado = listaNotas.getSelectedIndex();
        if (indiceSeleccionado != -1) {
            String nota = notas.get(indiceSeleccionado);
            areaTexto.setText(nota);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	AppNotas app = new AppNotas();
                app.setVisible(true);
            }
        });
    }
}
