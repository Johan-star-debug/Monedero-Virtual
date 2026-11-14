package uniquindio.edu.co.poo.monedero_virtual.Model;

import java.util.ArrayList;
import java.util.List;

public class CuentaPuntos {

    private int puntos;
    private List<MovimientoPuntos> movimientos;

    public CuentaPuntos() {
        puntos = 0;
        movimientos = new ArrayList<>();
    }

    public void acumularPuntos(int cantidad, String descripcion) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad de puntos debe ser positiva"
            );
        }

        puntos += cantidad;
        movimientos.add(
                new MovimientoPuntos(descripcion, cantidad)
        );
    }

    public boolean descontarPuntos(int cantidad, String descripcion) {
        if (cantidad <= 0 || puntos < cantidad) {
            return false;
        }

        puntos -= cantidad;
        movimientos.add(
                new MovimientoPuntos(descripcion, -cantidad)
        );

        return true;
    }

    public int getPuntos() {
        return puntos;
    }

    public List<MovimientoPuntos> getMovimientos() {
        return new ArrayList<>(movimientos);
    }
}
