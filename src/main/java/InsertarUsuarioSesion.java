package com.tuapp;

import com.tuapp.model.Usuario;
import com.tuapp.model.Sesion;
import com.tuapp.model.HibernateUtil;
import com.tuapp.model.dao.SesionDao;
import com.tuapp.model.dao.UsuarioDao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class InsertarUsuarioSesion extends JFrame {
    private JTextField nombreField;
    private JTextField correoField;

    // Definición de los DAOs
    private UsuarioDao usuarioDao; // Asegúrate de que tienes esta clase
    private SesionDao sesionDao; // Asegúrate de que tienes esta clase

    public InsertarUsuarioSesion() {
        // Inicializa los DAOs
        usuarioDao = new UsuarioDao(); // Asegúrate de que esta clase esté correctamente implementada
        sesionDao = new SesionDao(); // Asegúrate de que esta clase esté correctamente implementada

        // Configuración de la ventana
        setTitle("Insertar Usuario y Sesión");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear componentes
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField(20);

        JLabel correoLabel = new JLabel("Correo:");
        correoField = new JTextField(20);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarUsuarioSesion();
            }
        });

        // Layout
        JPanel panel = new JPanel();
        panel.add(nombreLabel);
        panel.add(nombreField);
        panel.add(correoLabel);
        panel.add(correoField);
        panel.add(guardarButton);

        add(panel);
    }

    private void guardarUsuarioSesion() {
        // Obtener el nombre y correo del usuario desde los campos de texto
        String nombre = nombreField.getText();
        String correo = correoField.getText();

        // Crear y guardar el usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);

        // Guardar en la base de datos
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(usuario);
            session.flush(); // Para obtener el ID del usuario guardado

            // Crear y guardar la sesión
            Sesion sesion = new Sesion();
            sesion.setUsuarioId(usuario.getId());

            // Obtener la fecha y hora actuales y convertir a LocalDateTime
            Date fechaActual = new Date();
            LocalDateTime localDateTime = fechaActual.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            sesion.setFechaInicio(localDateTime);
            session.save(sesion);
            transaction.commit();

            JOptionPane.showMessageDialog(this, "Usuario y sesión guardados correctamente.");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InsertarUsuarioSesion().setVisible(true));
    }
}
