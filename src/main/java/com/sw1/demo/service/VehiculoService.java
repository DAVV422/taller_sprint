package com.sw1.demo.service;

import com.sw1.demo.model.Vehiculo;
import com.sw1.demo.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculoService {

    @Autowired
    private final VehiculoRepository vehiculoRepository;

    public Vehiculo createVehiculo(Vehiculo vehiculo) {
    	List<Vehiculo> vehiculoDuplicado = vehiculoRepository.findByMatricula(vehiculo.getMatricula());
    	System.out.println(vehiculoDuplicado);
    	if(vehiculoDuplicado.isEmpty()) {
    		return vehiculoRepository.save(vehiculo);
    	}
    	return null;
    }

    public Vehiculo updateVehiculo(String id, Vehiculo vehiculoDetails) {
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElse(null);
        if (vehiculo != null) {
            vehiculo.setMatricula(vehiculoDetails.getMatricula() != null ? vehiculoDetails.getMatricula() : vehiculo.getMatricula());
            vehiculo.setColor(vehiculoDetails.getColor() != null ? vehiculoDetails.getColor() : vehiculo.getColor());
            vehiculo.setDescripcion(vehiculoDetails.getDescripcion() != null ? vehiculoDetails.getDescripcion() : vehiculo.getDescripcion());
            vehiculo.setModelo(vehiculoDetails.getModelo() != null ? vehiculoDetails.getModelo() : vehiculo.getModelo());            
            return vehiculoRepository.save(vehiculo);
        }
        return null;
    }
    
    public Vehiculo getVehiculoByMatricula(String matricula) {
        List<Vehiculo> vehiculo = vehiculoRepository.findByMatricula(matricula);
        if(vehiculo.isEmpty()) {
        	return null;
        }
        return vehiculo.get(0);
    }

    public List<Vehiculo> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    public Vehiculo getVehiculoById(String id) {
        return vehiculoRepository.findById(id).orElse(null);
    }

    public void deleteVehiculo(String id) {
        vehiculoRepository.deleteById(id);
    }
}