package com.sw1.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sw1.demo.model.Personal;
import com.sw1.demo.service.PersonaService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PersonalController {
	@Autowired
	private PersonaService personaService;

	@QueryMapping
    public List<Personal> getAllPersonal() {
		log.info("Query All Personal");
        return personaService.getAllPersonal();
    }

	@QueryMapping
    public Personal getPersonalById(@Argument String id) {
		log.info("Query Personal by Id {}:", id);
        return personaService.getUsuarioById(id);
    }

    @MutationMapping
    public Personal createPersonal(@Argument String nombre, @Argument String apellido, @Argument String ci, @Argument String direccion, @Argument String celular, @Argument String fechaNacimiento, @Argument String usuarioId) {
        Personal personal = new Personal();
        personal.setNombre(nombre);
        personal.setApellido(apellido);
        personal.setCi(ci);
        personal.setDireccion(direccion);
        personal.setCelular(celular);
        personal.setFechaNacimiento(fechaNacimiento);
        personal.setUsuarioId(usuarioId);
        log.info("Create Personal:", personal.toString());
        return personaService.createPersonal(personal);
    }

    @MutationMapping
    public Personal updatePersonal(@Argument String id, @Argument String nombre, @Argument String apellido, @Argument String ci, @Argument String direccion, @Argument String celular, @Argument String fechaNacimiento) {
        Personal personalDetails = new Personal();
        personalDetails.setNombre(nombre);
        personalDetails.setApellido(apellido);
        personalDetails.setCi(ci);
        personalDetails.setDireccion(direccion);
        personalDetails.setCelular(celular);
        personalDetails.setFechaNacimiento(fechaNacimiento);
        log.info("Create Personal:", personalDetails.toString());
        return personaService.updatePersonal(id, personalDetails);
    }

    @MutationMapping
    public Boolean deletePersonal(@Argument String id) {
        personaService.deleteUsuario(id);
        log.info("Query Delete Personal by Id {}:", id);
        return true;
    }
}
