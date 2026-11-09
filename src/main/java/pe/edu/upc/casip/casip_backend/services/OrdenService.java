package pe.edu.upc.casip.casip_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.casip.casip_backend.models.Orden;
import pe.edu.upc.casip.casip_backend.repositories.OrdenRepository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    public String crearOrden(Orden orden) throws ExecutionException, InterruptedException {
        if (orden.getIdOrden() == null || orden.getIdOrden().isEmpty()) {
            orden.setIdOrden(UUID.randomUUID().toString());
        }

        String updateTime = ordenRepository.guardarOrden(orden);
        return "Orden procesada y guardada correctamente a las: " + updateTime;
    }

    public List<Orden> obtenerTodasLasOrdenes() throws ExecutionException, InterruptedException {
        return ordenRepository.obtenerTodasLasOrdenes();
    }

    public Orden obtenerOrdenPorId(String id) throws ExecutionException, InterruptedException {
        return ordenRepository.obtenerOrdenPorId(id);
    }

    public String actualizarOrden(String id, Orden orden) throws ExecutionException, InterruptedException {
        String updateTime = ordenRepository.actualizarOrden(id, orden);
        return "Orden " + id + " actualizada correctamente a las: " + updateTime;
    }
}