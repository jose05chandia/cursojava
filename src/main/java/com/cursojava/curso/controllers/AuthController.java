package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    UsuarioDao usuarioDao;
    @Autowired
    JWTUtil jwtUtil;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){
        Usuario usuarioLogeado=usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if(usuarioLogeado!=null){
            String token=jwtUtil.create(String.valueOf(usuarioLogeado.getId()),usuarioLogeado.getEmail());
            return token;
        }
        return "FAIL";

    }
}
