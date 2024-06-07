package com.sw1.demo.controller;

import com.sw1.demo.model.Cliente;
import com.sw1.demo.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @QueryMapping
    public List<Cliente> getAllClientes() {
        log.info("Query All Clientes");
        return clienteService.getAllClientes();
    }

    @QueryMapping
    public Cliente getClienteById(@Argument String id) {
        log.info("Query Cliente by Id {}:", id);
        return clienteService.getClienteById(id);
    }

    @MutationMapping
    public Cliente createCliente(@Argument String nombre, @Argument String apellido, @Argument String celular, @Argument String nit, @Argument String usuarioId) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setCelular(celular);
        cliente.setNit(nit);
        cliente.setUsuarioId(usuarioId);
        log.info("Create Cliente:", cliente.toString());
        return clienteService.createCliente(cliente);
    }

    @MutationMapping
    public Cliente updateCliente(@Argument String id, @Argument String nombre, @Argument String apellido, @Argument String celular, @Argument String nit, @Argument String usuarioId) {
        Cliente clienteDetails = new Cliente();
        clienteDetails.setNombre(nombre);
        clienteDetails.setApellido(apellido);
        clienteDetails.setCelular(celular);
        clienteDetails.setNit(nit);        
        log.info("Update Cliente:", clienteDetails.toString());
        return clienteService.updateCliente(id, clienteDetails);
    }

    @MutationMapping
    public Boolean deleteCliente(@Argument String id) {
        clienteService.deleteCliente(id);
        log.info("Delete Cliente by Id {}:", id);
        return true;
    }
}