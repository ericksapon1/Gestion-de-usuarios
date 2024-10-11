package com.tuapp.main;

import com.tuapp.model.Usuario;
import com.tuapp.model.HibernateUtil;
import org.hibernate.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Crear y guardar un usuario de prueba
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan Pérez");
        usuario.setCorreo("juan.perez@example.com");

        session.save(usuario);

        session.getTransaction().commit();
        session.close();

        HibernateUtil.shutdown();
    }

    public static class MostrarUsuarios extends JFrame {

        private JTable table;
        private JButton btnVolver;

        public MostrarUsuarios() {
            // Configurar la ventana
            setTitle("Mostrar Usuarios");
            setSize(500, 400);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Crear la tabla con columnas
            String[] columnNames = {"ID", "Nombre", "Correo"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);

            // Botón para volver al menú principal
            btnVolver = new JButton("Volver");

            // Añadir componentes al layout
            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
            add(btnVolver, BorderLayout.SOUTH);

            // Obtener los datos de la base de datos y llenar la tabla
            llenarTablaUsuarios(tableModel);

            // Evento para volver al menú principal
            btnVolver.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Cierra la ventana actual
                }
            });
        }

        private void llenarTablaUsuarios(DefaultTableModel tableModel) {
            // Lógica para obtener la lista de usuarios de la base de datos
            java.util.List<Usuario> usuarios = obtenerUsuariosDesdeBaseDeDatos();

            // Llenar la tabla con los usuarios
            for (Usuario usuario : usuarios) {
                Object[] rowData = {usuario.getId(), usuario.getNombre(), usuario.getCorreo()};
                tableModel.addRow(rowData);
            }
        }

        private java.util.List<Usuario> obtenerUsuariosDesdeBaseDeDatos() {
            // Aquí debes implementar la lógica para obtener los usuarios desde Hibernate.
            // Por ahora, simulamos con usuarios de ejemplo.

            // TODO: Implementar lógica con Hibernate
            // Aquí deberías usar un EntityManager para consultar los usuarios desde la base de datos.

            // Simulamos usuarios para probar la interfaz
            Usuario usuario1 = new Usuario();
            usuario1.setId(1L);
            usuario1.setNombre("Juan Pérez");
            usuario1.setCorreo("juan.perez@example.com");

            Usuario usuario2 = new Usuario();
            usuario2.setId(2L);
            usuario2.setNombre("Ana Gómez");
            usuario2.setCorreo("ana.gomez@example.com");

            return List.of(usuario1, usuario2); // Retorna una lista de usuarios de ejemplo
        }

        public static void main(String[] args) {
            MostrarUsuarios ventana = new MostrarUsuarios();
            ventana.setVisible(true);
        }
    }
}
