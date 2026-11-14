package uniquindio.edu.co.poo.monedero_virtual.Model;

import uniquindio.edu.co.poo.monedero_virtual.Model.*;

import java.math.BigDecimal;

public class Retiro extends Transaccion
        implements ICalculablePuntos {

    private static final BigDecimal COMISION =
            new BigDecimal("5");

    public Retiro(String id, BigDecimal monto,
                  Monedero monedero, CuentaPuntos cuentaPuntos) {

        super(id, monto, monedero, cuentaPuntos,
                TipoTransaccion.RETIRO);
    }

    @Override
    public void ejecutar() {
        if (estado != EstadoTransaccion.PENDIENTE) {
            throw new IllegalStateException(
                    "La transacción ya fue procesada"
            );
        }

        BigDecimal total = monto.add(COMISION);
        origen.retirar(total);
        origen.agregarTransaccion(this);

        puntosGenerados = calcularPuntos(monto);
        cuentaPuntos.acumularPuntos(
                puntosGenerados, "Puntos por retiro"
        );

        estado = EstadoTransaccion.COMPLETADA;
    }

    @Override
    public int calcularPuntos(BigDecimal monto) {
        return (monto.intValue() / 100) * 2;
    }

    @Override
    public void revertir() {
        if (estado != EstadoTransaccion.COMPLETADA) {
            throw new IllegalStateException(
                    "No se puede revertir esta transacción"
            );
        }

        origen.depositar(monto.add(COMISION));

        cuentaPuntos.descontarPuntos(
                puntosGenerados, "Reversión de retiro"
        );

        estado = EstadoTransaccion.REVERTIDA;
    }

    public BigDecimal getComision() {
        return COMISION;
    }
}