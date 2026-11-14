package uniquindio.edu.co.poo.monedero_virtual.Model;

import uniquindio.edu.co.poo.monedero_virtual.Model.TipoNotificacion;

import java.time.LocalDateTime;

public class Notificacion {

    private String mensaje;
    private TipoNotificacion tipo;
    private LocalDateTime fecha;
    private boolean leida;

    public Notificacion(String mensaje, TipoNotificacion tipo) {
        this.mensaje = mensaje;
        this.tipo = tipo;
        this.fecha = LocalDateTime.now();
        this.leida = false;
    }

    public void marcarComoLeida() {
        leida = true;
    }

    public String getMensaje() {
        return mensaje;
    }

    public TipoNotificacion getTipo() {
        return tipo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public boolean isLeida() {
        return leida;
    }
}