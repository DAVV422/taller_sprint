package com.sw1.demo.service;

import com.sw1.demo.model.Cliente;
import com.sw1.demo.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    @Autowired
    private final ClienteRepository clienteRepository;

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(String id, Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            cliente.setNombre(clienteDetails.getNombre() != null ? clienteDetails.getNombre() : cliente.getNombre());
            cliente.setApellido(clienteDetails.getApellido() != null ? clienteDetails.getApellido() : cliente.getApellido());
            cliente.setCelular(clienteDetails.getCelular() != null ? clienteDetails.getCelular() : cliente.getCelular());
            cliente.setNit(clienteDetails.getNit() != null ? clienteDetails.getNit() : cliente.getNit());            
            return clienteRepository.save(cliente);
        }
        return null;
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(String id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public void deleteCliente(String id) {
        clienteRepository.deleteById(id);
    }
}