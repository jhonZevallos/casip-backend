package pe.edu.upc.casip.casip_backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orden {

    private String idOrden;
    private String cliente;
    private String direccion;
    private double latitud;
    private double longitud;
    private String estado;
    private String idTecnico;
    private String fechaProgramada;
}
