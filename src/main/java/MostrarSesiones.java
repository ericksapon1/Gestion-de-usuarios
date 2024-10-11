package com.tuapp;

import com.tuapp.model.HibernateUtil;
import com.tuapp.model.Sesion;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MostrarSesiones extends JFrame {

    private JTable tablaSesiones;

    public MostrarSesiones() {
        // Configuración de la ventana
        setTitle("Mostrar Sesiones");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Usuario ID");
        modelo.addColumn("Fecha de Inicio");

        // Crear la tabla
        tablaSesiones = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tablaSesiones);
        add(scrollPane, BorderLayout.CENTER);

        // Cargar las sesiones desde la base de datos
        cargarSesiones(modelo);
    }

    private void cargarSesiones(DefaultTableModel modelo) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Consulta para obtener todas las sesiones
            Query<Sesion> query = session.createQuery("FROM Sesion", Sesion.class);
            List<Sesion> sesiones = query.list();

            // Agregar cada sesión al modelo de la tabla
            for (Sesion sesion : sesiones) {
                Object[] row = new Object[3];
                row[0] = sesion.getId();
                row[1] = sesion.getUsuarioId();
                row[2] = sesion.getFechaInicio();
                modelo.addRow(row);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            JOptionPane.showMessageDialog(this, "Error al cargar las sesiones: " + e.getMessage());
        }
    }
}
