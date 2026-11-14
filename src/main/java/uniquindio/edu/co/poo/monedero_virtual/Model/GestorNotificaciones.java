package uniquindio.edu.co.poo.monedero_virtual.Model;

import uniquindio.edu.co.poo.monedero_virtual.Model.INotificable;
import uniquindio.edu.co.poo.monedero_virtual.Model.Monedero;
import uniquindio.edu.co.poo.monedero_virtual.Model.Notificacion;
import uniquindio.edu.co.poo.monedero_virtual.Model.TipoNotificacion;

import java.util.ArrayList;
import java.util.List;

public class GestorNotificaciones implements INotificable {

    private List<Notificacion> notificaciones;

    public GestorNotificaciones() {
        notificaciones = new ArrayList<>();
    }

    @Override
    public void enviarNotificacion(Notificacion notificacion) {
        if (notificacion != null) {
            notificaciones.add(notificacion);
        }
    }

    public void generarAlertaSaldoBajo(Monedero monedero) {
        if (monedero.getSaldo().compareTo(
                monedero.getLimiteSaldoBajo()) <= 0) {

            enviarNotificacion(new Notificacion(
                    "El saldo de " + monedero.getNombre()
                            + " está bajo.",
                    TipoNotificacion.SALDO_BAJO
            ));
        }
    }

    public List<Notificacion> getNotificaciones() {
        return new ArrayList<>(notificaciones);
    }
}