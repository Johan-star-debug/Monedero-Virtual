package uniquindio.edu.co.poo.monedero_virtual.Model;

import java.time.LocalDateTime;

public class MovimientoPuntos {

    private String descripcion;
    private int cantidad;
    private LocalDateTime fecha;

    public MovimientoPuntos(String descripcion, int cantidad) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}
