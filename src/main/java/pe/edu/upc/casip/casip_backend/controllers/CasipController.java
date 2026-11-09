package pe.edu.upc.casip.casip_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.casip.casip_backend.models.Orden;
import pe.edu.upc.casip.casip_backend.services.OrdenService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class CasipController {

    @Autowired
    private OrdenService ordenService;

    @PostMapping("/ordenes")
    public ResponseEntity<String> crearOrden(@RequestBody Orden orden) {
        try {
            String mensaje = ordenService.crearOrden(orden);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al crear la orden: " + e.getMessage());
        }
    }

    @GetMapping("/ordenes")
    public ResponseEntity<List<Orden>> listarOrdenes() {
        try {
            List<Orden> ordenes = ordenService.obtenerTodasLasOrdenes();

            if (ordenes.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(ordenes);

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/ordenes/{id}")
    public ResponseEntity<Orden> obtenerDetalleOrden(@PathVariable String id) {
        try {
            Orden orden = ordenService.obtenerOrdenPorId(id);
            if (orden != null) {
                return ResponseEntity.ok(orden);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/ordenes/{id}")
    public ResponseEntity<String> actualizarOrden(@PathVariable String id, @RequestBody Orden ordenActualizada) {
        try {
            // Primero verificamos si la orden existe antes de actualizarla
            Orden ordenExistente = ordenService.obtenerOrdenPorId(id);
            if (ordenExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La orden con ID " + id + " no existe.");
            }

            String mensaje = ordenService.actualizarOrden(id, ordenActualizada);
            return ResponseEntity.ok(mensaje);

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al actualizar la orden: " + e.getMessage());
        }
    }
}