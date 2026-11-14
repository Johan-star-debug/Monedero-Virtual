package uniquindio.edu.co.poo.monedero_virtual.Model;

import uniquindio.edu.co.poo.monedero_virtual.Model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Transaccion implements IReversible {

    protected String id;
    protected BigDecimal monto;
    protected LocalDateTime fecha;
    protected EstadoTransaccion estado;
    protected TipoTransaccion tipo;
    protected Monedero origen;
    protected CuentaPuntos cuentaPuntos;
    protected int puntosGenerados;

    public Transaccion(String id, BigDecimal monto,
                       Monedero origen, CuentaPuntos cuentaPuntos,
                       TipoTransaccion tipo) {

        if (monto == null ||
                monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Monto inválido");
        }

        this.id = id;
        this.monto = monto;
        this.origen = origen;
        this.cuentaPuntos = cuentaPuntos;
        this.tipo = tipo;
        this.fecha = LocalDateTime.now();
        this.estado = EstadoTransaccion.PENDIENTE;
        this.puntosGenerados = 0;
    }

    public abstract void ejecutar();

    public String getId() {
        return id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public EstadoTransaccion getEstado() {
        return estado;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public Monedero getOrigen() {
        return origen;
    }

    public int getPuntosGenerados() {
        return puntosGenerados;
    }
}
