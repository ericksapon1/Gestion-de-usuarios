package com.tuapp.model.dao;

import com.tuapp.model.Usuario;
import com.tuapp.model.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsuarioDao {

    public void save(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e; // Propaga la excepción
        }
    }

    public Usuario findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Usuario.class, id);
        }
    }

    // Aquí puedes agregar más métodos como update, delete, etc.
}
