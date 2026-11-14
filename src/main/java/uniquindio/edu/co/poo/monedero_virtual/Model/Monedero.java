package uniquindio.edu.co.poo.monedero_virtual.Model;

import uniquindio.edu.co.poo.monedero_virtual.Model.Transaccion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Monedero {

    private String id;
    private String nombre;
    private BigDecimal saldo;
    private BigDecimal limiteSaldoBajo;
    private List<Transaccion> historial;

    public Monedero(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.saldo = BigDecimal.ZERO;
        this.limiteSaldoBajo = new BigDecimal("100");
        this.historial = new ArrayList<>();
    }

    public void depositar(BigDecimal monto) {
        validarMonto(monto);
        saldo = saldo.add(monto);
    }

    public void retirar(BigDecimal monto) {
        validarMonto(monto);

        if (saldo.compareTo(monto) < 0) {
            throw new IllegalStateException("Saldo insuficiente");
        }

        saldo = saldo.subtract(monto);
    }

    public void agregarTransaccion(Transaccion transaccion) {
        historial.add(transaccion);
    }

    private void validarMonto(BigDecimal monto) {
        if (monto == null ||
                monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Monto inválido");
        }
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public BigDecimal getLimiteSaldoBajo() {
        return limiteSaldoBajo;
    }

    public void setLimiteSaldoBajo(BigDecimal limite) {
        if (limite == null ||
                limite.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Límite inválido");
        }

        limiteSaldoBajo = limite;
    }

    public List<Transaccion> getHistorial() {
        return new ArrayList<>(historial);
    }
}
