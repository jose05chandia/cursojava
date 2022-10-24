package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class UsuarioDaoImp implements  UsuarioDao{

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<Usuario> obtenerUsuarios() {
        String query="From Usuario";
        return entityManager.createQuery(query, Usuario.class).getResultList();
    }

    @Override
    public void crearUsuario(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public void editarUsuario() {

    }

    @Override
    public void eliminarsuario(Long id) {
        Usuario usuario=entityManager.find(Usuario.class,id);
        entityManager.remove(usuario);
    }

    @Override
    public Usuario getUsuario(Long id) {
        return null;
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query="From Usuario WHERE email=:email";
        List<Usuario> lista= entityManager.createQuery(query)
                .setParameter("email",usuario.getEmail())
                .getResultList();

        if(lista.isEmpty()){
            return null;
        }
        String passwordFromDB=lista.get(0).getPassword();
        Argon2 argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(passwordFromDB, usuario.getPassword())){
            return lista.get(0);
        }
        return  null;
    }
}
