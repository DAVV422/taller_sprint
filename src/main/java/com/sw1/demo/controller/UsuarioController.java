package com.sw1.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sw1.demo.model.Usuario;
import com.sw1.demo.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@QueryMapping
	public Usuario usuarioById(@Argument String id) {
		log.info("Query Usuario by Id {}:", id);
		return usuarioService.getUsuarioById(id);
	}
	
	@QueryMapping
	public List<Usuario> allUsuarios() {
		log.info("Query All Usuarios");
		return usuarioService.getAllUsuarios();
	}
	
	@QueryMapping
	public Usuario login(@Argument String email, @Argument String password) {
		log.info("login para email: ", email);
		return usuarioService.login(email, password);
	}
	
	@MutationMapping
	public Usuario createUsuario(@Argument String nombreUsuario, @Argument String email, @Argument String password, @Argument String tipo) {
		Usuario usuario = new Usuario();		
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setTipo(tipo);
        return usuarioService.createUsuario(usuario);
	}
	
	@MutationMapping
	public Usuario updateUsuario(@Argument String id, @Argument String nombreUsuario, @Argument String email, @Argument String password, @Argument String tipo) {
        Usuario usuarioDetails = new Usuario();
        usuarioDetails.setNombreUsuario(nombreUsuario);
        usuarioDetails.setEmail(email);
        usuarioDetails.setPassword(password);
        usuarioDetails.setTipo(tipo);
        return usuarioService.updateUsuario(id, usuarioDetails);
    }
	
	@MutationMapping
	public Boolean deleteUsuario(@Argument String id) {
        usuarioService.deleteUsuario(id);
        return true;
    }	
	
}
