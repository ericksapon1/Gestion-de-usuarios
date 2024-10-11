package com.tuapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Gestión de Usuarios y Sesiones");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnInsertarUsuario = new JButton("Insertar Usuario y Sesión");
        JButton btnMostrarUsuarios = new JButton("Mostrar Usuarios");
        JButton btnMostrarSesiones = new JButton("Mostrar Sesiones");
        JButton btnSalir = new JButton("Salir");

        setLayout(new GridLayout(4, 1, 10, 10));
        add(btnInsertarUsuario);
        add(btnMostrarUsuarios);
        add(btnMostrarSesiones);
        add(btnSalir);

        btnInsertarUsuario.addActionListener(e -> {
            InsertarUsuarioSesion insertarUsuarioSesion = new InsertarUsuarioSesion();
            insertarUsuarioSesion.setVisible(true);
        });

        JButton mostrarUsuariosButton = new JButton("Mostrar Usuarios");
        mostrarUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar la ventana de MostrarUsuarios
                MostrarUsuarios mostrarUsuarios = new MostrarUsuarios();
                mostrarUsuarios.setVisible(true); // Hacer la ventana visible
            }
        });

        JButton mostrarSesionesButton = new JButton("Mostrar Sesiones");
        mostrarSesionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear y mostrar la ventana de MostrarSesiones
                com.tuapp.MostrarSesiones mostrarSesiones = new com.tuapp.MostrarSesiones();
                mostrarSesiones.setVisible(true); // Hacer la ventana visible
            }
        });


        btnSalir.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
    }
}
