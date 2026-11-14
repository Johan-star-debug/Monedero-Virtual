package uniquindio.edu.co.poo.monedero_virtual.Model;

import java.time.LocalDateTime;

public class Canje {

    private Beneficio beneficio;
    private LocalDateTime fecha;

    public Canje(Beneficio beneficio) {
        this.beneficio = beneficio;
        this.fecha = LocalDateTime.now();
    }

    public Beneficio getBeneficio() {
        return beneficio;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}
