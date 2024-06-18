package com.sw1.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sw1.demo.model.DetalleSalida;
import com.sw1.demo.model.DetalleVentaProducto;
import com.sw1.demo.model.NotaVenta;
import com.sw1.demo.model.Producto;
import com.sw1.demo.model.Salida;
import com.sw1.demo.repository.DetalleSalidaRepository;
import com.sw1.demo.repository.DetalleVentaProductoRepository;
import com.sw1.demo.repository.NotaVentaRepository;
import com.sw1.demo.repository.SalidaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetalleVentaProductoService {

    @Autowired
    private final DetalleVentaProductoRepository detalleVentaProductoRepository;
    
    @Autowired
    private final NotaVentaRepository notaVentaRepository;
    
    @Autowired
    private final SalidaRepository salidaRepository;
    
    @Autowired
    private final DetalleSalidaRepository detalleSalidaRepository;
    
    @Autowired
    private final ProductoService productoService;
       

    public DetalleVentaProducto createDetalleVentaProducto(DetalleVentaProducto detalleVentaProducto) {
    	DetalleVentaProducto detalleCreado = detalleVentaProductoRepository.save(detalleVentaProducto);
        NotaVenta notaVenta = notaVentaRepository.findById(detalleVentaProducto.getNotaVentaId()).orElse(null);        
        notaVenta.setSubtotal(notaVenta.getSubtotal() + detalleVentaProducto.getMontoTotal());
        Double interes = notaVenta.getSubtotal() * notaVenta.getInteres() / 100;
        notaVenta.setTotal(notaVenta.getSubtotal() + interes);
        interes = detalleVentaProducto.getMontoTotal() * notaVenta.getInteres() / 100;
        notaVenta.setSaldo(notaVenta.getSaldo() + detalleVentaProducto.getMontoTotal() + interes);
        notaVentaRepository.save(notaVenta);
        if(notaVenta.getSalidaId() == null) {
        	Salida newSalida = new Salida();
        	newSalida.setFecha(notaVenta.getFecha());
        	newSalida.setHora(notaVenta.getHora());
        	newSalida.setMotivo("Venta con id: "+notaVenta.getId());
        	Salida salidaCreada = salidaRepository.save(newSalida);
        	DetalleSalida newDetalleSalida = new DetalleSalida();
        	newDetalleSalida.setCantidad(detalleVentaProducto.getCantidad());
        	newDetalleSalida.setNotaSalidaId(salidaCreada.getId());
        	newDetalleSalida.setProductoId(detalleVentaProducto.getProductoId());
        	detalleSalidaRepository.save(newDetalleSalida);
        	
        	//actualizar stock
            Producto producto=productoService.getProductoById(newDetalleSalida.getProductoId());
            producto.setStock((Integer.parseInt(producto.getStock())-newDetalleSalida.getCantidad())+"");
            productoService.updateProducto(newDetalleSalida.getProductoId(), producto);
        } else {
        	DetalleSalida newDetalleSalida = new DetalleSalida();
        	newDetalleSalida.setCantidad(detalleVentaProducto.getCantidad());
        	newDetalleSalida.setNotaSalidaId(notaVenta.getSalidaId());
        	newDetalleSalida.setProductoId(detalleVentaProducto.getProductoId());
        	detalleSalidaRepository.save(newDetalleSalida);
        	
        	//actualizar stock
            Producto producto=productoService.getProductoById(newDetalleSalida.getProductoId());
            producto.setStock((Integer.parseInt(producto.getStock())-newDetalleSalida.getCantidad())+"");
            productoService.updateProducto(newDetalleSalida.getProductoId(), producto);
        }
        return detalleCreado;
    }

    public DetalleVentaProducto getDetalleVentaProductoById(String id) {
        return detalleVentaProductoRepository.findById(id).orElse(null);
    }

    public List<DetalleVentaProducto> getAllDetalleVentaProductoOfNotaVenta(String notaVentaId) {
        return detalleVentaProductoRepository.findByNotaVentaId(notaVentaId);
    }

    public void deleteDetalleVentaProducto(String id) {
    	DetalleVentaProducto detalleVentaProducto = detalleVentaProductoRepository.findById(id).orElse(null);
    	String noteId = detalleVentaProducto.getNotaVentaId();
    	String productoId = detalleVentaProducto.getProductoId();
    	Double monto = detalleVentaProducto.getMontoTotal();
        detalleVentaProductoRepository.deleteById(id);
        
        NotaVenta notaVenta = notaVentaRepository.findById(noteId).orElse(null);		
        if (notaVenta != null) {
        	notaVenta.setSubtotal(notaVenta.getSubtotal() - monto); 
        	Double interes = notaVenta.getSubtotal() * notaVenta.getInteres() / 100;
        	notaVenta.setTotal(notaVenta.getSubtotal() + interes);
        	interes = monto * notaVenta.getInteres() / 100;
        	notaVenta.setSaldo(notaVenta.getSaldo() - monto - interes);
        	List<DetalleSalida> detallesSalidas = detalleSalidaRepository.findByNotaSalidaId(notaVenta.getSalidaId());        	
        	detallesSalidas.forEach(detalle -> {
        		if(detalle.getProductoId().equals(productoId)) {
        			//actualizar stock
                    Producto producto=productoService.getProductoById(productoId);
                    producto.setStock((Integer.parseInt(producto.getStock()) + detalle.getCantidad())+"");
                    productoService.updateProducto(productoId, producto);
                    
        			detalleSalidaRepository.deleteById(detalle.getId());
        			
        		}
        	});
        }        
        notaVentaRepository.save(notaVenta);
    }
}

