package uniquindio.edu.co.poo.monedero_virtual.Model;

import uniquindio.edu.co.poo.monedero_virtual.Model.*;

import java.math.BigDecimal;

public class Transferencia extends Transaccion
        implements ICalculablePuntos {

    private Monedero destino;

    private static final BigDecimal COMISION =
            new BigDecimal("2");

    public Transferencia(String id, BigDecimal monto,
                         Monedero origen, Monedero destino,
                         CuentaPuntos cuentaPuntos) {

        super(id, monto, origen, cuentaPuntos,
                TipoTransaccion.TRANSFERENCIA);

        if (destino == null || destino == origen) {
            throw new IllegalArgumentException(
                    "Monedero de destino inválido"
            );
        }

        this.destino = destino;
    }

    @Override
    public void ejecutar() {
        if (estado != EstadoTransaccion.PENDIENTE) {
            throw new IllegalStateException(
                    "La transacción ya fue procesada"
            );
        }

        origen.retirar(monto.add(COMISION));
        destino.depositar(monto);

        origen.agregarTransaccion(this);
        destino.agregarTransaccion(this);

        puntosGenerados = calcularPuntos(monto);
        cuentaPuntos.acumularPuntos(
                puntosGenerados, "Puntos por transferencia"
        );

        estado = EstadoTransaccion.COMPLETADA;
    }

    @Override
    public int calcularPuntos(BigDecimal monto) {
        return (monto.intValue() / 100) * 3;
    }

    @Override
    public void revertir() {
        if (estado != EstadoTransaccion.COMPLETADA) {
            throw new IllegalStateException(
                    "No se puede revertir esta transacción"
            );
        }

        if (destino.getSaldo().compareTo(monto) < 0) {
            throw new IllegalStateException(
                    "El destino no tiene saldo suficiente"
            );
        }

        destino.retirar(monto);
        origen.depositar(monto.add(COMISION));

        cuentaPuntos.descontarPuntos(
                puntosGenerados, "Reversión de transferencia"
        );

        estado = EstadoTransaccion.REVERTIDA;
    }

    public Monedero getDestino() {
        return destino;
    }

    public BigDecimal getComision() {
        return COMISION;
    }
}
