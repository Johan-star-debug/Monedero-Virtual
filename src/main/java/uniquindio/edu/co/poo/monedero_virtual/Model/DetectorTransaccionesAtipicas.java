package uniquindio.edu.co.poo.monedero_virtual.Model;

import java.util.List;

public class DetectorTransaccionesAtipicas {

    public boolean esAtipica(
            Transaccion transaccion,
            List<Transaccion> historial) {

        List<Double> valores = historial.stream()
                .filter(t -> t.getEstado() == EstadoTransaccion.COMPLETADA)
                .map(t -> t.getMonto().doubleValue())
                .toList();

        if (valores.size() < 2) {
            return false;
        }

        double promedio = valores.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0);

        double varianza = valores.stream()
                .mapToDouble(v -> Math.pow(v - promedio, 2))
                .average()
                .orElse(0);

        double desviacion = Math.sqrt(varianza);

        return transaccion.getMonto().doubleValue()
                > promedio + (2 * desviacion);
    }

    public Notificacion crearAlerta(Transaccion transaccion) {
        return new Notificacion(
                "Se detectó una operación atípica por valor de: "
                        + transaccion.getMonto(),
                TipoNotificacion.OPERACION_ATIPICA
        );
    }
}
