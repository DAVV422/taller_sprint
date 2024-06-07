package com.sw1.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sw1.demo.model.Personal;
import com.sw1.demo.repository.PersonalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonaService {

	@Autowired
	private final PersonalRepository personalRepository;
	
	public Personal createPersonal(Personal personal) {
		return personalRepository.save(personal);
	}
	
	public Personal updatePersonal(String id, Personal personalDetails) {
        Personal personal = personalRepository.findById(id).orElse(null);
        if(personal != null) {
        	personal.setNombre(personalDetails.getNombre() != null ?
        			personalDetails.getNombre() : personal.getNombre());
	        personal.setApellido(personalDetails.getApellido() != null ?
	        		personalDetails.getApellido() : personal.getApellido());
	        personal.setCi(personalDetails.getCi() != null ?
	        		personalDetails.getCi() : personal.getCi());
	        personal.setDireccion(personalDetails.getDireccion() != null ?
	        		personalDetails.getDireccion() : personal.getDireccion());
	        personal.setCelular(personalDetails.getCelular() != null ?
	        		personalDetails.getCelular() : personal.getCelular());
	        personal.setFechaNacimiento(personalDetails.getFechaNacimiento() != null ?
	        		personalDetails.getFechaNacimiento() : personal.getFechaNacimiento());
        }
        return personalRepository.save(personal);
    }
	
	public List<Personal> getAllPersonal() {
		return personalRepository.findAll();
	}
	 
	public Personal getUsuarioById(String id) {
		return personalRepository.findById(id).orElse(null);
	}
	
	public void deleteUsuario(String id) {
        personalRepository.deleteById(id);
    }
}
