package com.tuapp;

import com.tuapp.model.Usuario;
import com.tuapp.model.HibernateUtil;
import org.hibernate.Session;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MostrarUsuarios extends JFrame {
    private JTextArea textArea;

    public MostrarUsuarios() {
        // Configuración de la ventana
        setTitle("Mostrar Usuarios");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        cargarUsuarios();
    }

    private void cargarUsuarios() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Usuario> usuarios = session.createQuery("from Usuario", Usuario.class).list();
            StringBuilder sb = new StringBuilder();
            for (Usuario usuario : usuarios) {
                sb.append("ID: ").append(usuario.getId()).append(", Nombre: ").append(usuario.getNombre()).append(", Correo: ").append(usuario.getCorreo()).append("\n");
            }
            textArea.setText(sb.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar usuarios: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MostrarUsuarios().setVisible(true);
        });
    }
}
