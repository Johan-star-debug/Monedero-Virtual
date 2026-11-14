package uniquindio.edu.co.poo.monedero_virtual.Model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalizadorGastos {

    public BigDecimal calcularTotalGastos(List<Transaccion> transacciones) {
        return transacciones.stream()
                .filter(t -> t.getTipo() == TipoTransaccion.RETIRO
                        || t.getTipo() == TipoTransaccion.TRANSFERENCIA)
                .map(Transaccion::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public double calcularPromedio(List<Transaccion> transacciones) {
        return transacciones.stream()
                .mapToDouble(t -> t.getMonto().doubleValue())
                .average()
                .orElse(0);
    }

    public Map<TipoTransaccion, List<Transaccion>> agruparPorTipo(
            List<Transaccion> transacciones) {

        return transacciones.stream()
                .collect(Collectors.groupingBy(Transaccion::getTipo));
    }

    public List<Transaccion> ordenarPorMonto(
            List<Transaccion> transacciones) {

        return transacciones.stream()
                .sorted((a, b) -> b.getMonto().compareTo(a.getMonto()))
                .toList();
    }
}