package com.sw1.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sw1.demo.model.Usuario;
import com.sw1.demo.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
	
	@Autowired
	private final UsuarioRepository usuarioRepository;
	
	public Usuario createUsuario(Usuario usuario) {
		System.out.println(usuario.toString());
		return usuarioRepository.save(usuario);
	}
	
	public Usuario updateUsuario(String id, Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
        	usuario.setNombreUsuario(usuarioDetails.getNombreUsuario() != null ? 
        			usuarioDetails.getNombreUsuario() : usuario.getNombreUsuario());
            usuario.setEmail(usuarioDetails.getEmail() != null ?
            		usuarioDetails.getEmail() : usuario.getEmail());
            usuario.setPassword(usuarioDetails.getPassword() != null ? 
            		usuarioDetails.getPassword() : usuario.getPassword());
            usuario.setTipo(usuarioDetails.getTipo() != null ? 
            		usuarioDetails.getTipo() : usuario.getTipo());
            return usuarioRepository.save(usuario);
        }
        return null;
    }
	
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}
	 
	public Usuario getUsuarioById(String id) {
		return usuarioRepository.findById(id).orElse(null);
	}
	
	public void deleteUsuario(String id) {
        usuarioRepository.deleteById(id);
    }
	
	public Usuario login(String email, String password) {
		Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);		
		return usuario;
	}
	 
}
