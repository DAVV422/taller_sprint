package com.sw1.demo.service;

import com.sw1.demo.model.Marca;
import com.sw1.demo.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcaService {

    @Autowired
    private final MarcaRepository marcaRepository;

    public Marca createMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca updateMarca(String id, Marca marcaDetails) {
        Marca marca = marcaRepository.findById(id).orElse(null);
        if (marca != null) {
            marca.setNombre(marcaDetails.getNombre() != null ? marcaDetails.getNombre() : marca.getNombre());
            marca.setPorcentaje(marcaDetails.getPorcentaje() != null ? marcaDetails.getPorcentaje() : marca.getPorcentaje());
            return marcaRepository.save(marca);
        }
        return null;
    }

    public List<Marca> getAllMarcas() {
        return marcaRepository.findAll();
    }

    public Marca getMarcaById(String id) {
        return marcaRepository.findById(id).orElse(null);
    }

    public void deleteMarca(String id) {
        marcaRepository.deleteById(id);
    }
}