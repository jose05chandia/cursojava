package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    JWTUtil jwtUtil;

    private boolean validarToken(String token){
         String idUsusario=jwtUtil.getKey(token);
        return idUsusario!=null;
    }

    @RequestMapping(value = "usuarios",method = RequestMethod.GET)
    public List<Usuario> list(@RequestHeader(value = "Authorization") String token){
        if(!validarToken(token)){return  null;};
        return usuarioDao.obtenerUsuarios();
    }

    @RequestMapping(value = "usuario/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable  Long id, @RequestHeader(value = "Authorization") String token){
        if(!validarToken(token)){return;};
        usuarioDao.eliminarsuario(id);
    }

    @RequestMapping(value = "usuario",method = RequestMethod.POST)
    public void create(@RequestBody Usuario usuario, @RequestHeader(value = "Authorization") String token){

        if(!validarToken(token)){return;};
        Argon2 argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash=argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.crearUsuario(usuario);
    }





}
