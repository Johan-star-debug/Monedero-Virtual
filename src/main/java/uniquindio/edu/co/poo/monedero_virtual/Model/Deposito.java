package uniquindio.edu.co.poo.monedero_virtual.Model;

import java.math.BigDecimal;

public class Deposito extends Transaccion
        implements ICalculablePuntos {

    public Deposito(String id, BigDecimal monto,
                    Monedero monedero, CuentaPuntos cuentaPuntos) {

        super(id, monto, monedero, cuentaPuntos,
                TipoTransaccion.DEPOSITO);
    }

    @Override
    public void ejecutar() {
        if (estado != EstadoTransaccion.PENDIENTE) {
            throw new IllegalStateException(
                    "La transacción ya fue procesada"
            );
        }

        origen.depositar(monto);
        origen.agregarTransaccion(this);

        puntosGenerados = calcularPuntos(monto);
        cuentaPuntos.acumularPuntos(
                puntosGenerados, "Puntos por depósito"
        );

        estado = EstadoTransaccion.COMPLETADA;
    }

    @Override
    public int calcularPuntos(BigDecimal monto) {
        return monto.intValue() / 100;
    }

    @Override
    public void revertir() {
        if (estado != EstadoTransaccion.COMPLETADA) {
            throw new IllegalStateException(
                    "No se puede revertir esta transacción"
            );
        }

        if (origen.getSaldo().compareTo(monto) < 0) {
            throw new IllegalStateException(
                    "Saldo insuficiente para revertir"
            );
        }

        origen.retirar(monto);
        cuentaPuntos.descontarPuntos(
                puntosGenerados, "Reversión de depósito"
        );

        estado = EstadoTransaccion.REVERTIDA;
    }
}