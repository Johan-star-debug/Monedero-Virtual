package uniquindio.edu.co.poo.monedero_virtual.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransaccionProgramada implements IProcesable {

    private String id;
    private Monedero origen;
    private Monedero destino;
    private BigDecimal monto;
    private Periodicidad periodicidad;
    private LocalDateTime proximaFecha;
    private EstadoProgramacion estado;
    private CuentaPuntos cuentaPuntos;

    public TransaccionProgramada(
            String id,
            Monedero origen,
            Monedero destino,
            BigDecimal monto,
            Periodicidad periodicidad,
            LocalDateTime proximaFecha,
            CuentaPuntos cuentaPuntos) {

        if (monto == null ||
                monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Monto inválido");
        }

        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.monto = monto;
        this.periodicidad = periodicidad;
        this.proximaFecha = proximaFecha;
        this.cuentaPuntos = cuentaPuntos;
        this.estado = EstadoProgramacion.ACTIVA;
    }

    @Override
    public void procesar() {
        if (estado != EstadoProgramacion.ACTIVA) {
            return;
        }

        if (LocalDateTime.now().isBefore(proximaFecha)) {
            return;
        }

        if (destino == null) {
            new Deposito(
                    id, monto, origen, cuentaPuntos
            ).ejecutar();
        } else {
            new Transferencia(
                    id, monto, origen, destino, cuentaPuntos
            ).ejecutar();
        }

        actualizarFecha();
    }

    private void actualizarFecha() {
        switch (periodicidad) {
            case DIARIA:
                proximaFecha = proximaFecha.plusDays(1);
                break;
            case SEMANAL:
                proximaFecha = proximaFecha.plusWeeks(1);
                break;
            case MENSUAL:
                proximaFecha = proximaFecha.plusMonths(1);
                break;
        }
    }

    public void pausar() {
        estado = EstadoProgramacion.PAUSADA;
    }

    public void activar() {
        estado = EstadoProgramacion.ACTIVA;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getProximaFecha() {
        return proximaFecha;
    }

    public EstadoProgramacion getEstado() {
        return estado;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }
}