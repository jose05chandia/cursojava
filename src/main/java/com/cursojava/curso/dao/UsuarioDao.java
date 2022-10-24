package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> obtenerUsuarios();
    void crearUsuario(Usuario usuario);
    void editarUsuario();
    void eliminarsuario(Long id);
    Usuario getUsuario(Long id);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
